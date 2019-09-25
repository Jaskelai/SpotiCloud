package com.github.kornilovmikhail.spoticloud.interactors

import com.github.kornilovmikhail.spoticloud.domain.interactors.CurrentTrackUseCase
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.CurrentTrackRepository
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CurrentTrackUseCaseTest {

    @Mock private lateinit var currentTrackRepository: CurrentTrackRepository
    @Mock private lateinit var track: Track

    private lateinit var currentTrackUseCase: CurrentTrackUseCase

    @Before
    fun setup() {
        currentTrackUseCase = CurrentTrackUseCase(currentTrackRepository)
    }

    @Test
    fun `test setCurrentTrack() will call repository's saveCurrentTrack()`() {

        currentTrackUseCase.setCurrentTrack(track)

        verify(currentTrackRepository, times(1)).setCurrentTrack(track)
    }


    @Test
    fun `test observeCurrentTrack() will return observable`() {
        given(currentTrackRepository.observeCurrentTrack())
            .willReturn(Observable.just(track))

        val trackObserver = currentTrackUseCase.observeCurrentTrack().test()

        trackObserver.assertValue(track)

        trackObserver.dispose()
    }
}
