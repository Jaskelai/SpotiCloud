package com.github.kornilovmikhail.spoticloud.data.repository

import com.github.kornilovmikhail.spoticloud.data.network.tokenhelper.TokenHelperSpotify
import com.github.kornilovmikhail.spoticloud.domain.interfaces.UserSpotifyRepository
import com.spotify.sdk.android.authentication.AuthenticationResponse
import javax.inject.Inject

class UserSpotifyRepositoryImpl @Inject constructor(
    private val tokenHelperSpotify: TokenHelperSpotify
) : UserSpotifyRepository {

    override fun auth(any: Any?) {
        val token = (any as AuthenticationResponse).accessToken
        tokenHelperSpotify.saveToken(token)
    }

    override fun checkAuth(): Boolean {
        return tokenHelperSpotify.getToken() != null
    }
}
