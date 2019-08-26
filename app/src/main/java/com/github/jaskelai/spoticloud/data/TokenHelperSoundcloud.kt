package com.github.jaskelai.spoticloud.data

import com.github.jaskelai.spoticloud.data.local.sharedprefs.SharedPreferencesStorage
import javax.inject.Inject

class TokenHelperSoundcloud @Inject constructor(
    private val sharedPreferencesStorage: SharedPreferencesStorage
) {

    companion object {
        private const val KEY_TOKEN_SOUNDCLOUD = "key_token_soundcloud"
        private const val KEY_REFRESH_TOKEN_SOUNDCLOUD = "key_refresh_token_soundcloud"
    }

    fun getToken(): String? = sharedPreferencesStorage.readMessage(KEY_TOKEN_SOUNDCLOUD)

    fun saveToken(token: String) {
        sharedPreferencesStorage.writeMessage(KEY_TOKEN_SOUNDCLOUD, token)
    }

    fun getRefreshToken(): String? {
        return sharedPreferencesStorage.readMessage(KEY_REFRESH_TOKEN_SOUNDCLOUD)
    }

    fun saveRefreshToken(token: String) {
        sharedPreferencesStorage.writeMessage(KEY_REFRESH_TOKEN_SOUNDCLOUD, token)
    }
}
