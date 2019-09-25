package com.github.kornilovmikhail.spoticloud.interactors

import com.github.kornilovmikhail.spoticloud.domain.interactors.CommonAuthUseCase
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.CommonUserRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CommonAuthUseCaseTest {

    @Mock private lateinit var commonUserRepository: CommonUserRepository

    private lateinit var commonAuthUseCase: CommonAuthUseCase

    @Before
    fun setup() {
        commonAuthUseCase = CommonAuthUseCase(commonUserRepository)
    }

    @Test
    fun `test checkAuth() will return true`() {
        val expectedValue = true

        given(commonUserRepository.isAuthed())
            .willReturn(true)

        val actualValue = commonAuthUseCase.isAuthed()

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `test saveAuthStatus() will call repository's saveAuthStatus()`() {
        val status = true

        commonAuthUseCase.saveAuthStatus(status)

        verify(commonUserRepository, times(1)).saveAuthStatus(status)
    }
}
