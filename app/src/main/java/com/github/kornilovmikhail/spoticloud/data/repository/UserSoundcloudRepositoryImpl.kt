package com.github.kornilovmikhail.spoticloud.data.repository

import com.github.kornilovmikhail.spoticloud.BuildConfig
import com.github.kornilovmikhail.spoticloud.data.TokenHelperSoundcloud
import com.github.kornilovmikhail.spoticloud.data.network.api.SoundCloudNotAuthedApi
import com.github.kornilovmikhail.spoticloud.domain.interfaces.UserSoundcloudRepository
import io.reactivex.Completable
import javax.inject.Inject

class UserSoundcloudRepositoryImpl @Inject constructor(
    private val soundCloudNotAuthedApi: SoundCloudNotAuthedApi,
    private val tokenHelperSoundcloud: TokenHelperSoundcloud
) : UserSoundcloudRepository {

    companion object {
        private const val SOUNDCLOUD_CLIENT_ID = BuildConfig.SOUNDCLOUD_CLIENT_ID
        private const val SOUNDCLOUD_CLIENT_SECRET = BuildConfig.SOUNDCLOUD_CLIENT_SECRET
        private const val SOUNDCLOUD_GRANT_TYPE = "password"
    }

    override fun auth(email: String, password: String): Completable {
        return soundCloudNotAuthedApi.getToken(
            email,
            password,
            SOUNDCLOUD_CLIENT_ID,
            SOUNDCLOUD_CLIENT_SECRET,
            SOUNDCLOUD_GRANT_TYPE
        )
            .map {
                tokenHelperSoundcloud.saveToken(it.accessToken)
                tokenHelperSoundcloud.saveRefreshToken(it.refreshToken)
            }
            .ignoreElement()
    }

    override fun checkAuth(): Boolean {
        return tokenHelperSoundcloud.getToken() != null
    }
}
