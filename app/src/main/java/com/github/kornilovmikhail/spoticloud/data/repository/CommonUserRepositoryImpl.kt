package com.github.kornilovmikhail.spoticloud.data.repository

import com.github.kornilovmikhail.spoticloud.data.local.sharedprefs.SharedPreferencesStorage
import com.github.kornilovmikhail.spoticloud.domain.interfaces.CommonUserRepository
import io.reactivex.Maybe
import javax.inject.Inject

class CommonUserRepositoryImpl @Inject constructor(
    private val sharedPreferencesStorage: SharedPreferencesStorage
) : CommonUserRepository {

    companion object {
        private const val IS_AUTHED = "is_authed"
    }

    override fun checkAuth(): Maybe<Boolean> =
        sharedPreferencesStorage.readMessage(IS_AUTHED)
            .map {
                it.toBoolean()
            }

    override fun saveAuthStatus(isAuthed: Boolean) {
        sharedPreferencesStorage.writeMessage(IS_AUTHED, isAuthed.toString())
    }
}
