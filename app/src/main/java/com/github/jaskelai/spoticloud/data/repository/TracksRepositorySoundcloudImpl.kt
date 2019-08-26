package com.github.jaskelai.spoticloud.data.repository

import com.github.jaskelai.spoticloud.data.mappers.mapSoundCloudTrackResponseToTrack
import com.github.jaskelai.spoticloud.data.network.api.SoundCloudAuthedApi
import com.github.jaskelai.spoticloud.domain.interfaces.TracksRepository
import com.github.jaskelai.spoticloud.domain.model.Track
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
