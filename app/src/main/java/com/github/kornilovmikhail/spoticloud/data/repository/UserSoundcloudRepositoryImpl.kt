package com.github.kornilovmikhail.spoticloud.data.repository

import android.content.Context
import com.github.kornilovmikhail.spoticloud.BuildConfig
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.data.network.api.SoundCloudNotAuthedApi
import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TokenHelper
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.UserSoundcloudRepository
import io.reactivex.Completable
import retrofit2.HttpException
import javax.inject.Inject

class UserSoundcloudRepositoryImpl @Inject constructor(
    private val soundCloudNotAuthedApi: SoundCloudNotAuthedApi,
    @param:SoundCloudQualifier private val tokenHelperSoundcloud: TokenHelper,
    private val appContext: Context
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
            .map { tokenHelperSoundcloud.saveToken(it) }
            .ignoreElement()
            .onErrorResumeNext {
                if (it is HttpException) {
                    Completable.error(Throwable(appContext.getString(R.string.error_server)))
                } else {
                    Completable.error(Throwable(appContext.getString(R.string.no_connection)))
                }
            }
    }

    override fun isAuthed(): Boolean {
        return tokenHelperSoundcloud.getToken() != null
    }
}
