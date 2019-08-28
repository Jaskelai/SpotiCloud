package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.data.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.data.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TracksRepository
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class FavTracksUseCase @Inject constructor(
    @param:SoundCloudQualifier private val soundCloudRepository: TracksRepository,
    @param:SpotifyQualifier private val spotifyRepository: TracksRepository
) {

    fun getFavTracks(): Single<List<Track>> {
        return Single.zip(soundCloudRepository.getFavTracks(),
            spotifyRepository.getFavTracks(),
            BiFunction { soundcloudTracks, spotifyTracks -> (soundcloudTracks + spotifyTracks) })
    }
}
