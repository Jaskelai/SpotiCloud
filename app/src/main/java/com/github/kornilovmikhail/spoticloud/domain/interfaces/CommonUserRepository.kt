package com.github.kornilovmikhail.spoticloud.domain.interfaces

interface CommonUserRepository {

    fun checkAuth(): Boolean

    fun saveAuthStatus(isAuthed: Boolean)
}
