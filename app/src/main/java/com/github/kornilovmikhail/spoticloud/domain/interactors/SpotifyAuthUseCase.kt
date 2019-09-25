package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.UserSpotifyRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SpotifyAuthUseCase @Inject constructor(
    private val userSpotifyRepository: UserSpotifyRepository
) {

    fun auth(code: String): Completable = userSpotifyRepository.auth(code)
        .subscribeOn(Schedulers.io())

    fun checkAuth(): Boolean = userSpotifyRepository.isAuthed()

    fun getAuthUrl(): String = userSpotifyRepository.getAuthUrl()
}
