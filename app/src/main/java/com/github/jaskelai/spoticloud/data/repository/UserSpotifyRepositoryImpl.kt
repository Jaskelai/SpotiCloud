package com.github.jaskelai.spoticloud.data.repository

import com.github.jaskelai.spoticloud.data.TokenHelperSoundcloud
import com.github.jaskelai.spoticloud.domain.interfaces.UserSpotifyRepository
import com.spotify.sdk.android.authentication.AuthenticationResponse
import io.reactivex.Completable
import javax.inject.Inject

class UserSpotifyRepositoryImpl @Inject constructor(
    private val tokenHelperSoundcloud: TokenHelperSoundcloud
) : UserSpotifyRepository {

    override fun auth(any: Any?): Completable {
        return Completable.fromAction {
            val token = (any as AuthenticationResponse).accessToken
            tokenHelperSoundcloud.saveToken(token)
        }
    }

    override fun checkAuth(): Boolean {
        return tokenHelperSoundcloud.getToken() != null
    }
}
