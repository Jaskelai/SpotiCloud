package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.TracksRepository
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.UserSpotifyRepository
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single
import javax.inject.Inject

class TrendsSpotifyUseCase @Inject constructor(
    @param:SpotifyQualifier private val spotifyTrackRepository: TracksRepository,
    private val userSpotifyRepository: UserSpotifyRepository
) {

    fun getTrendsTracks(): Single<List<Track>> {
        return if (userSpotifyRepository.isAuthed()) {
            spotifyTrackRepository.getTrendTracks()
        } else {
            Single.just(emptyList())
        }
    }
}
