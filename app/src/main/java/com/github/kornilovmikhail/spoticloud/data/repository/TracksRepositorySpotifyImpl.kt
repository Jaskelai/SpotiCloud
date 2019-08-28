package com.github.kornilovmikhail.spoticloud.data.repository

import com.github.kornilovmikhail.spoticloud.data.mappers.mapSpotifyTrackRemoteToTrack
import com.github.kornilovmikhail.spoticloud.data.network.api.SpotifyAuthedApi
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TracksRepository
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TracksRepositorySpotifyImpl @Inject constructor(
    private val spotifyAuthedApi: SpotifyAuthedApi
) : TracksRepository {
    override fun getFavTracks(): Single<List<Track>> {
        return spotifyAuthedApi.getFavoriteTracks()
            .map {
                it.items.map { track -> mapSpotifyTrackRemoteToTrack(track.track) }
            }
            .subscribeOn(Schedulers.io())
    }
}
