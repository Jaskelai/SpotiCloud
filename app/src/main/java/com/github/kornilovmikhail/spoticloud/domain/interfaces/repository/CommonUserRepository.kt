package com.github.kornilovmikhail.spoticloud.domain.interfaces.repository

interface CommonUserRepository {

    fun isAuthed(): Boolean

    fun saveAuthStatus(isAuthed: Boolean)
}
