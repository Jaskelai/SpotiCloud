package com.github.jaskelai.spoticloud.domain.interactors

import com.github.jaskelai.spoticloud.domain.interfaces.UserSoundcloudRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SoundCloudAuthUseCase @Inject constructor(
    private val userSoundcloudRepository: UserSoundcloudRepository
) {

    fun auth(email: String, password: String): Completable {
        return userSoundcloudRepository.auth(email, password)
            .subscribeOn(Schedulers.io())
    }

    fun checkAuth(): Boolean = userSoundcloudRepository.checkAuth()
}