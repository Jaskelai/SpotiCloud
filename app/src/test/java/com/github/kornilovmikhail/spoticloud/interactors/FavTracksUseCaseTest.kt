package com.github.kornilovmikhail.spoticloud.interactors

import com.github.kornilovmikhail.spoticloud.domain.interactors.FavTracksUseCase
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.TracksRepository
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.UserSoundcloudRepository
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.UserSpotifyRepository
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavTracksUseCaseTest {

    @Mock private lateinit var soundcloudTrackRepository: TracksRepository
    @Mock private lateinit var spotifyTrackRepository: TracksRepository
    @Mock private lateinit var userSoundcloudRepository: UserSoundcloudRepository
    @Mock private lateinit var userSpotifyRepository: UserSpotifyRepository

    @Mock private lateinit var track: Track

    private lateinit var favTracksUseCase: FavTracksUseCase

    @Before
    fun setUp() {
        favTracksUseCase = FavTracksUseCase(
            soundcloudTrackRepository,
            spotifyTrackRepository,
            userSoundcloudRepository,
            userSpotifyRepository
        )
    }

    @Test
    fun `test getFavTracks() all not authed will return empty list`() {
        val expectedValue = emptyList<Track>()

        given(userSoundcloudRepository.isAuthed())
            .willReturn(false)
        given(userSpotifyRepository.isAuthed())
            .willReturn(false)

        val trackListObserver = favTracksUseCase.getFavTracks().test()

        trackListObserver.assertValue(expectedValue)
    }

    @Test
    fun `test getFavTracks() soundcloud authed will return list with one track`() {
        val soundcloudTracks = arrayListOf(track)

        val expectedValue = arrayListOf(track)

        given(userSoundcloudRepository.isAuthed())
            .willReturn(true)
        given(userSpotifyRepository.isAuthed())
            .willReturn(false)
        given(soundcloudTrackRepository.getFavTracks())
            .willReturn(Single.just(soundcloudTracks))

        val trackListObserver = favTracksUseCase.getFavTracks().test()

        trackListObserver.assertValue(expectedValue)
    }

    @Test
    fun `test getFavTracks() spotify authed will return list with one track`() {
        val spotifyTracks = arrayListOf(track)

        val expectedValue = arrayListOf(track)

        given(userSoundcloudRepository.isAuthed())
            .willReturn(false)
        given(userSpotifyRepository.isAuthed())
            .willReturn(true)
        given(spotifyTrackRepository.getFavTracks())
            .willReturn(Single.just(spotifyTracks))

        val trackListObserver = favTracksUseCase.getFavTracks().test()

        trackListObserver.assertValue(expectedValue)
    }

    @Test
    fun `test getFavTracks() all authed will return list with two tracks`() {
        val soundcloudTracks = arrayListOf(track)
        val spotifyTracks = arrayListOf(track)

        val expectedValue = arrayListOf(track, track)

        given(userSoundcloudRepository.isAuthed())
            .willReturn(true)
        given(userSpotifyRepository.isAuthed())
            .willReturn(true)
        given(soundcloudTrackRepository.getFavTracks())
            .willReturn(Single.just(soundcloudTracks))
        given(spotifyTrackRepository.getFavTracks())
            .willReturn(Single.just(spotifyTracks))

        val trackListObserver = favTracksUseCase.getFavTracks().test()

        trackListObserver.assertValue(expectedValue)
    }
}
