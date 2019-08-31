package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TracksRepository
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class FavTracksUseCase @Inject constructor(
    @param:SoundCloudQualifier private val soundCloudTrackRepository: TracksRepository,
    @param:SpotifyQualifier private val spotifyTrackRepository: TracksRepository
) {

    fun getFavTracks(): Single<List<Track>> {
        return Single.zip(soundCloudTrackRepository.getFavTracks(),
            spotifyTrackRepository.getFavTracks(),
            BiFunction { soundcloudTracks, spotifyTracks -> (soundcloudTracks + spotifyTracks) })
    }
}
