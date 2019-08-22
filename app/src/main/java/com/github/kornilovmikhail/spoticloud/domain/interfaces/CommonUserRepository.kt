package com.github.kornilovmikhail.spoticloud.domain.interfaces

import io.reactivex.Maybe

interface CommonUserRepository {

    fun checkAuth(): Maybe<Boolean>

    fun saveAuthStatus(isAuthed: Boolean)
}
