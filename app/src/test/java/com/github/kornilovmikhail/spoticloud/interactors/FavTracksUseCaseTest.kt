package com.github.kornilovmikhail.spoticloud.interactors

import com.github.kornilovmikhail.spoticloud.domain.interactors.FavTracksUseCase
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TracksRepository
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavTracksUseCaseTest {

    @Mock
    private lateinit var soundcloudTrackRepository: TracksRepository
    @Mock
    private lateinit var spotifyTrackRepository: TracksRepository

    private lateinit var favTracksUseCase: FavTracksUseCase

    @Before
    fun setUp() {
        favTracksUseCase = FavTracksUseCase(soundcloudTrackRepository, spotifyTrackRepository)
    }

    @Test
    fun `test getFavTracks() will return empty list`() {
        val soundcloudTracks = emptyList<Track>()
        val spotifyTracks = emptyList<Track>()

        val expectedValue = emptyList<Track>()

        given(soundcloudTrackRepository.getFavTracks())
            .willReturn(Single.just(soundcloudTracks))
        given(spotifyTrackRepository.getFavTracks())
            .willReturn(Single.just(spotifyTracks))

        val trackListObserver = favTracksUseCase.getFavTracks().test()

        trackListObserver.assertValue(expectedValue)
    }

    @Test
    fun `test getFavTracks() will return list with one track`() {
        val track = mock(Track::class.java)
        val soundcloudTracks = arrayListOf<Track>(track)
        val spotifyTracks = emptyList<Track>()

        val expectedValue = arrayListOf<Track>(track)

        given(soundcloudTrackRepository.getFavTracks())
            .willReturn(Single.just(soundcloudTracks))
        given(spotifyTrackRepository.getFavTracks())
            .willReturn(Single.just(spotifyTracks))

        val trackListObserver = favTracksUseCase.getFavTracks().test()

        trackListObserver.assertValue(expectedValue)
    }
}
