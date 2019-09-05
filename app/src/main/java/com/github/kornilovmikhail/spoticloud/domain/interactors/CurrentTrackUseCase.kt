package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.domain.interfaces.CurrentTrackRepository
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Observable
import javax.inject.Inject

class CurrentTrackUseCase @Inject constructor(
    private val currentTrackRepository: CurrentTrackRepository
) {

    fun setCurrentTrack(track: Track) {
        currentTrackRepository.setCurrentTrack(track)
    }

    fun observeCurrentTrack(): Observable<Track> {
        return currentTrackRepository.observeCurrentTrack()
    }
}
