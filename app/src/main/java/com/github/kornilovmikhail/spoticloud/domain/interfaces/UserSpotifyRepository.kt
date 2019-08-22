package com.github.kornilovmikhail.spoticloud.domain.interfaces

import io.reactivex.Completable
import io.reactivex.Maybe

interface UserSpotifyRepository{

    fun auth(any: Any?): Completable

    fun getToken(): Maybe<String>
}
