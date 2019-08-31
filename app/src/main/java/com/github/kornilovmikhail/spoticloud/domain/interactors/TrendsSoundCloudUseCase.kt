package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TracksRepository
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single
import javax.inject.Inject

class TrendsSoundCloudUseCase @Inject constructor(
    @param:SoundCloudQualifier private val soundCloudTrackRepository: TracksRepository
) {

    fun getTrendsTracks(): Single<List<Track>> {
        return soundCloudTrackRepository.getTrendTracks()
    }
}
