package com.github.kornilovmikhail.spoticloud.data

import com.github.kornilovmikhail.spoticloud.data.local.sharedprefs.SharedPreferencesStorage
import javax.inject.Inject

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
