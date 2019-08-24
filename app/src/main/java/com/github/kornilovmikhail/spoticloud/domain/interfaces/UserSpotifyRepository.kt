package com.github.kornilovmikhail.spoticloud.domain.interfaces

import io.reactivex.Completable

interface UserSpotifyRepository{

    fun auth(any: Any?): Completable

    fun getToken(): String?
}
