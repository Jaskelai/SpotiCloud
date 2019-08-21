package com.github.kornilovmikhail.spoticloud.domain.interfaces

import io.reactivex.Completable
import io.reactivex.Maybe

interface UserSoundcloudRepository {

    fun auth(email: String, password: String): Completable

    fun loadSavedToken(): Maybe<String>
}
