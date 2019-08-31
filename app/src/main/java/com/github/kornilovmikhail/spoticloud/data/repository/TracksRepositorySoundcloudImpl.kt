package com.github.kornilovmikhail.spoticloud.data.repository

import com.github.kornilovmikhail.spoticloud.data.local.db.dao.TrackDao
import com.github.kornilovmikhail.spoticloud.data.mappers.mapSoundCloudTrackResponseToTrack
import com.github.kornilovmikhail.spoticloud.data.mappers.mapTrackDBToTrack
import com.github.kornilovmikhail.spoticloud.data.mappers.mapTrackToTrackDB
import com.github.kornilovmikhail.spoticloud.data.network.api.SoundCloudAuthedApi
import com.github.kornilovmikhail.spoticloud.data.network.api.SoundCloudV2AuthedApi
import com.github.kornilovmikhail.spoticloud.data.network.model.soundcloud.AuthorSoundCloudRemote
import com.github.kornilovmikhail.spoticloud.data.network.model.soundcloud.TrackSoundCloudResponse
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TracksRepository
import com.github.kornilovmikhail.spoticloud.domain.model.StreamServiceEnum
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TracksRepositorySoundcloudImpl @Inject constructor(
    private val soundCloudAuthedApi: SoundCloudAuthedApi,
    private val soundCloudV2AuthedApi: SoundCloudV2AuthedApi,
    private val trackDao: TrackDao
) : TracksRepository {

    companion object {
        private const val KIND_TOP = "top"
        private const val GENRE_ALL_TRACKS = "soundcloud:genres:all-music"
        private const val LIMIT = 100
    }

    override fun getFavTracks(): Single<List<Track>> {
        return soundCloudAuthedApi.getFavoriteTracks()
            .map {
                it.map { track -> mapSoundCloudTrackResponseToTrack(track) }
            }
            .flatMap {
                trackDao.upsertSoundcloudTracks(it.map { track -> mapTrackToTrackDB(track) })
                trackDao.findTracksByStreamService(StreamServiceEnum.SOUNDCLOUD)
            }
            .onErrorResumeNext {
                trackDao.findTracksByStreamService(StreamServiceEnum.SOUNDCLOUD)
            }
            .map {
                it.map { trackDB -> mapTrackDBToTrack(trackDB) }
            }
            .subscribeOn(Schedulers.io())
    }

    override fun getTrendTracks(): Single<List<Track>> {
        return soundCloudV2AuthedApi.getTrendsTracks(KIND_TOP, GENRE_ALL_TRACKS, LIMIT)
            .map {
                it.collection.map { trackContainer ->
                    trackContainer.track.uri.substringAfterLast(
                        "/"
                    )
                }
            }
            .map {
                it.map { id -> getTrackById(id) }
            }
            .flatMap {
                Single.zip(it) { list ->
                    list.map { track -> track as Track }
                        .filter { track ->
                            track.title != ""
                        }
                }
            }
            .subscribeOn(Schedulers.io())
    }

    private fun getTrackById(id: String): Single<Track> {
        return soundCloudAuthedApi.getTrack(id)
            .onErrorResumeNext {
                Single.just(TrackSoundCloudResponse(0, 0, 0, "", "", "", AuthorSoundCloudRemote("", "")))
            }
            .map {
                mapSoundCloudTrackResponseToTrack(it)
            }
            .subscribeOn(Schedulers.io())
    }
}
