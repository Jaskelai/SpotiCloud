package com.github.kornilovmikhail.spoticloud.data.network.tokenhelper

import com.github.kornilovmikhail.spoticloud.BuildConfig
import com.github.kornilovmikhail.spoticloud.data.local.sharedprefs.SharedPreferencesStorage
import com.github.kornilovmikhail.spoticloud.data.network.api.SoundCloudNotAuthedApi
import com.github.kornilovmikhail.spoticloud.data.network.model.soundcloud.TokenSoundCloudResponse
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@AppScope
class TokenHelperSoundcloud @Inject constructor(
    private val sharedPreferencesStorage: SharedPreferencesStorage,
    private val soundCloudNotAuthedApi: SoundCloudNotAuthedApi
) {

    companion object {
        private const val KEY_TOKEN_SOUNDCLOUD = "key_token_soundcloud"
        private const val KEY_REFRESH_TOKEN_SOUNDCLOUD = "key_refresh_token_soundcloud"
        private const val SOUNDCLOUD_CLIENT_ID = BuildConfig.SOUNDCLOUD_CLIENT_ID
        private const val SOUNDCLOUD_CLIENT_SECRET = BuildConfig.SOUNDCLOUD_CLIENT_SECRET
    }

    fun getToken(): String? = sharedPreferencesStorage.readMessage(KEY_TOKEN_SOUNDCLOUD)

    fun saveToken(tokenResponse: TokenSoundCloudResponse) {
        sharedPreferencesStorage.writeMessage(KEY_TOKEN_SOUNDCLOUD, tokenResponse.accessToken)
        sharedPreferencesStorage.writeMessage(
            KEY_REFRESH_TOKEN_SOUNDCLOUD,
            tokenResponse.refreshToken
        )
    }

    fun refresh() {
        val refreshToken = getRefreshToken()
        refreshToken?.let {
            soundCloudNotAuthedApi.getTokenByRefreshToken(
                SOUNDCLOUD_CLIENT_ID,
                SOUNDCLOUD_CLIENT_SECRET,
                it
            )
                .subscribeOn(Schedulers.io())
                .doOnSuccess { tokenResponse -> saveToken(tokenResponse) }
                .blockingGet()
        }
    }

    private fun getRefreshToken(): String? {
        return sharedPreferencesStorage.readMessage(KEY_REFRESH_TOKEN_SOUNDCLOUD)
    }
}
