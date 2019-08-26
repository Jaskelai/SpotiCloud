package com.github.jaskelai.spoticloud.data.repository

import com.github.jaskelai.spoticloud.data.local.sharedprefs.SharedPreferencesStorage
import com.github.jaskelai.spoticloud.domain.interfaces.CommonUserRepository
import javax.inject.Inject

class CommonUserRepositoryImpl @Inject constructor(
    private val sharedPreferencesStorage: SharedPreferencesStorage
) : CommonUserRepository {

    companion object {
        private const val IS_AUTHED = "is_authed"
    }

    override fun checkAuth(): Boolean {
        return sharedPreferencesStorage.readMessage(IS_AUTHED)?.toBoolean() ?: false
    }

    override fun saveAuthStatus(isAuthed: Boolean) {
        sharedPreferencesStorage.writeMessage(IS_AUTHED, isAuthed.toString())
    }
}