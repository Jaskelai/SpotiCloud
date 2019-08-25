package com.github.jaskelai.spoticloud.data.repository

import com.github.jaskelai.spoticloud.BuildConfig
import com.github.jaskelai.spoticloud.data.local.sharedprefs.SharedPreferencesStorage
import com.github.jaskelai.spoticloud.data.network.api.SoundCloudApi
import com.github.jaskelai.spoticloud.domain.interfaces.UserSoundcloudRepository
import io.reactivex.Completable
import javax.inject.Inject

class UserSoundcloudRepositoryImpl @Inject constructor(
    private val soundCloudApi: SoundCloudApi,
    private val sharedPreferencesStorage: SharedPreferencesStorage
) : UserSoundcloudRepository {

    companion object {
        private const val KEY_TOKEN_SOUNDCLOUD = "key_token_soundcloud"
        private const val KEY_REFRESH_TOKEN_SOUNDCLOUD = "key_refresh_token_soundcloud"
        private const val SOUNDCLOUD_CLIENT_ID = BuildConfig.SOUNDCLOUD_CLIENT_ID
        private const val SOUNDCLOUD_CLIENT_SECRET = BuildConfig.SOUNDCLOUD_CLIENT_SECRET
        private const val SOUNDCLOUD_GRANT_TYPE = "password"
    }

    override fun auth(email: String, password: String): Completable {
        return soundCloudApi.getToken(
            email,
            password,
            SOUNDCLOUD_CLIENT_ID,
            SOUNDCLOUD_CLIENT_SECRET,
            SOUNDCLOUD_GRANT_TYPE
        )
            .map {
                sharedPreferencesStorage.writeMessage(KEY_TOKEN_SOUNDCLOUD, it.accessToken)
                sharedPreferencesStorage.writeMessage(KEY_REFRESH_TOKEN_SOUNDCLOUD, it.refreshToken)
            }
            .ignoreElement()
    }

    override fun getToken(): String? {
        return sharedPreferencesStorage.readMessage(KEY_TOKEN_SOUNDCLOUD)
    }
}
