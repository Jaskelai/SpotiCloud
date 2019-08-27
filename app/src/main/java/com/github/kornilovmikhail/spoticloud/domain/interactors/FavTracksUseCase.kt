package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.domain.interfaces.TracksRepository
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavTracksUseCase @Inject constructor(
    private val soundCloudRepository: TracksRepository
) {

    fun getFavTracks(): Single<List<Track>> {
        return soundCloudRepository.getFavTracks()
            .subscribeOn(Schedulers.io())
    }
}
