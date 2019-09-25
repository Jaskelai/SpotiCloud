package com.github.kornilovmikhail.spoticloud.domain.interfaces.repository

import io.reactivex.Completable

interface UserSpotifyRepository{

    fun auth(code: String): Completable

    fun isAuthed(): Boolean

    fun getAuthUrl(): String
}
