package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.domain.interfaces.UserSpotifyRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SpotifyAuthUseCase @Inject constructor(
    private val userSpotifyRepository: UserSpotifyRepository
) {

    fun auth(any: Any?): Completable = userSpotifyRepository.auth(any)

    fun checkAuth(): Single<Boolean> = userSpotifyRepository.getToken()
        .isEmpty
        .map { !it }
}
