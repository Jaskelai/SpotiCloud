package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.domain.interfaces.UserSoundcloudRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SoundCloudAuthUseCase @Inject constructor(
    private val userSoundcloudRepository: UserSoundcloudRepository
) {

    fun auth(email: String, password: String): Completable =
        userSoundcloudRepository.auth(email, password)
            .subscribeOn(Schedulers.io())

    fun loadSavedToken(): Maybe<String> = userSoundcloudRepository.loadSavedToken()
}
