package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.TracksRepository
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.UserSoundcloudRepository
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.UserSpotifyRepository
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class TrackSearchUseCase @Inject constructor(
    @param:SoundCloudQualifier private val soundCloudTrackRepository: TracksRepository,
    @param:SpotifyQualifier private val spotifyTrackRepository: TracksRepository,
    private val userSoundcloudRepository: UserSoundcloudRepository,
    private val userSpotifyRepository: UserSpotifyRepository
) {

    fun searchForTracks(text: String): Single<List<Track>> {
        val soundcloudSearchedTracks: Single<List<Track>> =
            if (userSoundcloudRepository.isAuthed()) {
                soundCloudTrackRepository.searchForTracks(text)
            } else {
                Single.just(emptyList())
            }
        val spotifySearchedTracks: Single<List<Track>> = if (userSpotifyRepository.isAuthed()) {
            spotifyTrackRepository.searchForTracks(text)
        } else {
            Single.just(emptyList())
        }
        return Single.zip(spotifySearchedTracks,
            soundcloudSearchedTracks,
            BiFunction { soundcloudTracks, spotifyTracks -> (soundcloudTracks + spotifyTracks) })
    }
}
