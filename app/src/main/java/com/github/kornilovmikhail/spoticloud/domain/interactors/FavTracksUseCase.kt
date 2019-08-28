package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.data.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.data.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TracksRepository
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class FavTracksUseCase @Inject constructor(
    @field:SoundCloudQualifier private val soundCloudRepository: TracksRepository,
    @field:SpotifyQualifier private val spotifyRepository: TracksRepository
) {

    fun getFavTracks(): Single<List<Track>> {
        return soundCloudRepository.getFavTracks()
            .zipWith(
                spotifyRepository.getFavTracks(),
                BiFunction { soundCloudTracks, spotifyTracks -> soundCloudTracks + spotifyTracks })
    }
}
