package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.domain.interfaces.UserSpotifyRepository
import javax.inject.Inject

class SpotifyAuthUseCase @Inject constructor(
    private val userSpotifyRepository: UserSpotifyRepository
) {

    fun auth(any: Any?) = userSpotifyRepository.auth(any)

    fun checkAuth(): Boolean = userSpotifyRepository.checkAuth()
}
