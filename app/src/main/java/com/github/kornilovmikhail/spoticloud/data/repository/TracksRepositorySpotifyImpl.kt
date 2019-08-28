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
    override fun getFavTracks(): Single<List<Track>> {
        return spotifyAuthedApi.getFavoriteTracks()
            .map {
                it.items.map { track -> mapSpotifyTrackRemoteToTrack(track.track) }
            }
            .flatMap {
                trackDao.upsertSpotifyTracks(it.map { track -> mapTrackToTrackDB(track) })
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
}
