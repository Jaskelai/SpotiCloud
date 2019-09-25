package com.github.kornilovmikhail.spoticloud.data.repository

import android.content.Context
import android.util.Base64
import com.github.kornilovmikhail.spoticloud.BuildConfig
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.data.network.api.SpotifyNotAuthedApi
import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TokenHelper
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.UserSpotifyRepository
import io.reactivex.Completable
import retrofit2.HttpException
import javax.inject.Inject

class UserSpotifyRepositoryImpl @Inject constructor(
    @param:SpotifyQualifier private val tokenHelperSpotify: TokenHelper,
    private val spotifyNotAuthedApi: SpotifyNotAuthedApi,
    private val appContext: Context
) : UserSpotifyRepository {

    companion object {
        private const val BASE_AUTH_URL = "https://accounts.spotify.com/authorize"
        private const val CLIENT_ID = BuildConfig.SPOTIFY_CLIENT_ID
        private const val CLIENT_SECRET = BuildConfig.SPOTIFY_CLIENT_SECRET
        private const val RESPONSE_TYPE = "code"
        private const val REDIRECT_URI = "https://jaskelai.com/spoticloud/callback/"
        private const val SCOPE_LIBRARY_READ = "user-library-read"
        private const val SCOPE_LIBRARY_MODIFY = "user-library-modify"
        private const val SCOPE_USER_READ_PRIVATE = "user-read-private"
        private const val SCOPE_STREAMING = "streaming"
        private const val AUTHORIZATION_CODE = "authorization_code"
    }

    override fun auth(code: String): Completable {
        val clientIdAndSecret = "$CLIENT_ID:$CLIENT_SECRET"
        val encodedClientIdAndSecret =
            Base64.encodeToString(clientIdAndSecret.toByteArray(), Base64.NO_WRAP)
        val authorization = "Basic $encodedClientIdAndSecret"

        return spotifyNotAuthedApi.getToken(
            AUTHORIZATION_CODE,
            code,
            REDIRECT_URI,
            authorization
        )
            .map {
                tokenHelperSpotify.saveToken(it)
            }
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
        return tokenHelperSpotify.getToken() != null
    }

    override fun getAuthUrl(): String {
        return "$BASE_AUTH_URL?client_id=$CLIENT_ID&response_type=$RESPONSE_TYPE&redirect_uri=$REDIRECT_URI&scopes=$SCOPE_LIBRARY_READ $SCOPE_LIBRARY_MODIFY $SCOPE_USER_READ_PRIVATE $SCOPE_STREAMING"
    }
}
