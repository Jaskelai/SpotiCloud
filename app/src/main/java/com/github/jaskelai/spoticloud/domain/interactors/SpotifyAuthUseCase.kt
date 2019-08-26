package com.github.jaskelai.spoticloud.domain.interactors

import com.github.jaskelai.spoticloud.domain.interfaces.UserSpotifyRepository
import io.reactivex.Completable
import javax.inject.Inject

class SpotifyAuthUseCase @Inject constructor(
    private val userSpotifyRepository: UserSpotifyRepository
) {

    fun auth(any: Any?): Completable = userSpotifyRepository.auth(any)

    fun checkAuth(): Boolean = userSpotifyRepository.checkAuth()
}