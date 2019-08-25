package com.github.jaskelai.spoticloud.data.repository

import com.github.jaskelai.spoticloud.data.local.sharedprefs.SharedPreferencesStorage
import com.github.jaskelai.spoticloud.domain.interfaces.UserSpotifyRepository
import com.spotify.sdk.android.authentication.AuthenticationResponse
import io.reactivex.Completable
import javax.inject.Inject

class UserSpotifyRepositoryImpl @Inject constructor(
    private val sharedPreferencesStorage: SharedPreferencesStorage
) : UserSpotifyRepository {

    companion object {
        private const val KEY_TOKEN_SPOTIFY = "key_token_spotify"
    }

    override fun auth(any: Any?): Completable {
        return Completable.fromAction {
            val token = (any as AuthenticationResponse).accessToken
            sharedPreferencesStorage.writeMessage(KEY_TOKEN_SPOTIFY, token)
        }
    }

    override fun getToken(): String? {
        return sharedPreferencesStorage.readMessage(KEY_TOKEN_SPOTIFY)
    }
}
