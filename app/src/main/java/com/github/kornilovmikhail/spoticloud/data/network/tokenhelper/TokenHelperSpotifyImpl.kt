package com.github.kornilovmikhail.spoticloud.data.network.tokenhelper

import android.util.Base64
import com.github.kornilovmikhail.spoticloud.BuildConfig
import com.github.kornilovmikhail.spoticloud.data.local.sharedprefs.SharedPreferencesStorage
import com.github.kornilovmikhail.spoticloud.data.network.api.SpotifyNotAuthedApi
import com.github.kornilovmikhail.spoticloud.data.network.model.TokenResponse
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TokenHelper
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TokenHelperSpotifyImpl @Inject constructor(
    private val sharedPreferencesStorage: SharedPreferencesStorage,
    private val spotifyNotAuthedApi: SpotifyNotAuthedApi
) : TokenHelper {

    companion object {
        private const val KEY_TOKEN_SPOTIFY = "key_token_spotify"
        private const val KEY_REFRESH_TOKEN_SPOTIFY = "key_refresh_token_spotify"
        private const val SPOTIFY_CLIENT_ID = BuildConfig.SPOTIFY_CLIENT_ID
        private const val SPOTIFY_CLIENT_SECRET = BuildConfig.SPOTIFY_CLIENT_SECRET
        private const val SPOTIFY_GRANT_TYPE = "refresh_token"
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
        val clientIdAndSecret = "${SPOTIFY_CLIENT_ID}:${SPOTIFY_CLIENT_SECRET}"
        val encodedClientIdAndSecret =
            Base64.encodeToString(clientIdAndSecret.toByteArray(), Base64.NO_WRAP)
        val authorization = "Basic $encodedClientIdAndSecret"

        getRefreshToken()?.let {
            spotifyNotAuthedApi.getTokenByRefreshToken(
                SPOTIFY_GRANT_TYPE,
                it,
                authorization
            )
                .subscribeOn(Schedulers.io())
                .doOnSuccess { tokenResponse -> saveToken(tokenResponse) }
                .blockingGet()
        }
    }

    private fun getRefreshToken(): String? {
        return sharedPreferencesStorage.readMessage(KEY_REFRESH_TOKEN_SPOTIFY)
    }
}
