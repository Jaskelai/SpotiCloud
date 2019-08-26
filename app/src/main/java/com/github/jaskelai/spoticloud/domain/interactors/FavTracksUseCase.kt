package com.github.jaskelai.spoticloud.domain.interactors

import com.github.jaskelai.spoticloud.domain.interfaces.TracksRepository
import com.github.jaskelai.spoticloud.domain.model.Track
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
