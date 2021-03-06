package com.github.kornilovmikhail.spoticloud.data.repository

import com.github.kornilovmikhail.spoticloud.data.local.sharedprefs.SharedPreferencesStorageImpl
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.CommonUserRepository
import javax.inject.Inject

class CommonUserRepositoryImpl @Inject constructor(
    private val sharedPreferencesStorage: SharedPreferencesStorageImpl
) : CommonUserRepository {

    companion object {
        private const val IS_AUTHED = "is_authed"
    }

    override fun isAuthed(): Boolean {
        return sharedPreferencesStorage.readMessage(IS_AUTHED)?.toBoolean() ?: false
    }

    override fun saveAuthStatus(isAuthed: Boolean) {
        sharedPreferencesStorage.writeMessage(IS_AUTHED, isAuthed.toString())
    }
}
