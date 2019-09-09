package com.github.kornilovmikhail.spoticloud.data.repository

import com.github.kornilovmikhail.spoticloud.data.local.db.dao.TrackDao
import com.github.kornilovmikhail.spoticloud.data.mappers.mapSpotifyTrackRemoteToTrack
import com.github.kornilovmikhail.spoticloud.data.mappers.mapTrackDBToTrack
import com.github.kornilovmikhail.spoticloud.data.mappers.mapTrackToTrackDB
import com.github.kornilovmikhail.spoticloud.data.network.api.SpotifyAuthedApi
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TracksRepository
import com.github.kornilovmikhail.spoticloud.domain.model.StreamServiceEnum
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TracksRepositorySpotifyImpl @Inject constructor(
    private val spotifyAuthedApi: SpotifyAuthedApi,
    private val trackDao: TrackDao
) : TracksRepository {

    companion object {
        private const val TRENDS_PLAYLIST_ID = "37i9dQZEVXbMDoHDwVN2tF"
        private const val TYPE_TRACK = "track"
    }

    override fun getFavTracks(): Single<List<Track>> {
        return spotifyAuthedApi.getFavoriteTracks()
            .map {
                it.items.map { track -> mapSpotifyTrackRemoteToTrack(track.track) }
            }
            .flatMap {
                trackDao.upsertTracksbyStreamService(
                    it.map { track -> mapTrackToTrackDB(track) },
                    StreamServiceEnum.SPOTIFY
                )
                trackDao.findTracksByStreamService(StreamServiceEnum.SPOTIFY)
            }
            .onErrorResumeNext {
                trackDao.findTracksByStreamService(StreamServiceEnum.SPOTIFY)
            }
            .map {
                it.map { trackDB -> mapTrackDBToTrack(trackDB) }
            }
            .subscribeOn(Schedulers.io())
    }

    override fun getTrendTracks(): Single<List<Track>> {
        return spotifyAuthedApi.getTracksFromPlaylist(TRENDS_PLAYLIST_ID)
            .map {
                it.items.map { trackRemoteContainer ->
                    mapSpotifyTrackRemoteToTrack(trackRemoteContainer.track)
                }
            }
            .subscribeOn(Schedulers.io())
    }

    override fun searchForTracks(text: String): Single<List<Track>> {
        return spotifyAuthedApi.searchForTracks(text, TYPE_TRACK)
            .map {
                it.tracks.items.map { trackRemote -> mapSpotifyTrackRemoteToTrack(trackRemote) }
            }
            .subscribeOn(Schedulers.io())
    }
}
