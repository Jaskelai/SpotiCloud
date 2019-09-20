package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TracksRepository
import com.github.kornilovmikhail.spoticloud.domain.interfaces.UserSoundcloudRepository
import com.github.kornilovmikhail.spoticloud.domain.interfaces.UserSpotifyRepository
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class FavTracksUseCase @Inject constructor(
    @param:SoundCloudQualifier private val soundCloudTrackRepository: TracksRepository,
    @param:SpotifyQualifier private val spotifyTrackRepository: TracksRepository,
    private val userSoundcloudRepository: UserSoundcloudRepository,
    private val userSpotifyRepository: UserSpotifyRepository
) {

    fun getFavTracks(): Single<List<Track>> {
        val soundcloudFavTracks: Single<List<Track>> = if (userSoundcloudRepository.isAuthed()) {
            soundCloudTrackRepository.getFavTracks()
        } else {
            Single.just(emptyList())
        }
        val spotifyFavTracks: Single<List<Track>> = if (userSpotifyRepository.isAuthed()) {
            spotifyTrackRepository.getFavTracks()
        } else {
            Single.just(emptyList())
        }
        return Single.zip(soundcloudFavTracks,
            spotifyFavTracks,
            BiFunction { soundcloudTracks, spotifyTracks -> (soundcloudTracks + spotifyTracks) })
    }
}
