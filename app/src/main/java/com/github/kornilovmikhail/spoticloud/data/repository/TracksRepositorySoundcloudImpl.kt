package com.github.kornilovmikhail.spoticloud.data.repository

import com.github.kornilovmikhail.spoticloud.data.mappers.mapSoundCloudTrackResponseToTrack
import com.github.kornilovmikhail.spoticloud.data.network.api.SoundCloudAuthedApi
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TracksRepository
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single
import javax.inject.Inject

class TracksRepositorySoundcloudImpl @Inject constructor(
    private val soundCloudAuthedApi: SoundCloudAuthedApi
) : TracksRepository {

    override fun getFavTracks(): Single<List<Track>> {
        return soundCloudAuthedApi.getFavoriteTracks()
            .map {
                it.map { track -> mapSoundCloudTrackResponseToTrack(track)}
            }
    }
}
