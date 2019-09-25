package com.github.kornilovmikhail.spoticloud.data.network.tokenhelper

import com.github.kornilovmikhail.spoticloud.data.local.sharedprefs.SharedPreferencesStorage
import com.github.kornilovmikhail.spoticloud.data.network.model.TokenResponse
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TokenHelper
import javax.inject.Inject

class TokenHelperSpotifyImpl @Inject constructor(
    private val sharedPreferencesStorage: SharedPreferencesStorage
) : TokenHelper {

    companion object {
        private const val KEY_TOKEN_SPOTIFY = "key_token_spotify"
        private const val KEY_REFRESH_TOKEN_SPOTIFY = "key_refresh_token_spotify"
    }

    override fun getToken(): String? = sharedPreferencesStorage.readMessage(KEY_TOKEN_SPOTIFY)

    override fun saveToken(tokenResponse: TokenResponse) {
        sharedPreferencesStorage.writeMessage(KEY_TOKEN_SPOTIFY, tokenResponse.accessToken)
        sharedPreferencesStorage.writeMessage(
            KEY_REFRESH_TOKEN_SPOTIFY,
            tokenResponse.refreshToken
        )
    }

    override fun refresh() {
    }
}
