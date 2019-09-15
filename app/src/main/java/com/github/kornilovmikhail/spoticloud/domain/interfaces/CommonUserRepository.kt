package com.github.kornilovmikhail.spoticloud.domain.interfaces

interface CommonUserRepository {

    fun isAuthed(): Boolean

    fun saveAuthStatus(isAuthed: Boolean)
}
