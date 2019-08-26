package com.github.jaskelai.spoticloud.domain.interfaces

import io.reactivex.Completable

interface UserSpotifyRepository{

    fun auth(any: Any?): Completable

    fun checkAuth(): Boolean
}
