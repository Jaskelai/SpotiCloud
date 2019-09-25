package com.github.kornilovmikhail.spoticloud.domain.interfaces.repository

import io.reactivex.Completable

interface UserSoundcloudRepository {

    fun auth(email: String, password: String): Completable

    fun isAuthed(): Boolean
}
