package com.github.jaskelai.spoticloud.domain.interfaces

interface CommonUserRepository {

    fun checkAuth(): Boolean

    fun saveAuthStatus(isAuthed: Boolean)
}
