package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.TracksRepository
import com.github.kornilovmikhail.spoticloud.domain.model.StreamServiceEnum
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Completable
import javax.inject.Inject

class AddToFavTracksUseCase @Inject constructor(
    @param:SoundCloudQualifier private val soundCloudTrackRepository: TracksRepository,
    @param:SpotifyQualifier private val spotifyTrackRepository: TracksRepository
) {

    fun addToFavTracks(track: Track): Completable {
        return when (track.streamService) {
            StreamServiceEnum.SOUNDCLOUD -> soundCloudTrackRepository.addTrackToFav(track)
            StreamServiceEnum.SPOTIFY -> spotifyTrackRepository.addTrackToFav(track)
        }
    }
}
