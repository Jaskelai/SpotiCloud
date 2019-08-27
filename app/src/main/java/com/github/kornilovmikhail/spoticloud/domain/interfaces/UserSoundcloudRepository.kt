package com.github.kornilovmikhail.spoticloud.domain.interfaces

import io.reactivex.Completable

interface UserSoundcloudRepository {

    fun auth(email: String, password: String): Completable

    fun checkAuth(): Boolean
}
