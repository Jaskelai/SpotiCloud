package com.github.kornilovmikhail.spoticloud.data.network.tokenhelper

import com.github.kornilovmikhail.spoticloud.data.local.sharedprefs.SharedPreferencesStorage
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import javax.inject.Inject

@AppScope
class TokenHelperSpotify @Inject constructor(
    private val sharedPreferencesStorage: SharedPreferencesStorage
) {

    companion object {
        private const val KEY_TOKEN_SPOTIFY = "key_token_spotify"
    }

    fun getToken(): String? = sharedPreferencesStorage.readMessage(KEY_TOKEN_SPOTIFY)

    fun saveToken(token: String) {
        sharedPreferencesStorage.writeMessage(KEY_TOKEN_SPOTIFY, token)
    }
}
