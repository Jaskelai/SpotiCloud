package com.github.kornilovmikhail.spoticloud.data.repository

import com.github.kornilovmikhail.spoticloud.data.TokenHelperSoundcloud
import com.github.kornilovmikhail.spoticloud.domain.interfaces.UserSpotifyRepository
import com.spotify.sdk.android.authentication.AuthenticationResponse
import javax.inject.Inject

class UserSpotifyRepositoryImpl @Inject constructor(
    private val tokenHelperSoundcloud: TokenHelperSoundcloud
) : UserSpotifyRepository {

    override fun auth(any: Any?) {
        val token = (any as AuthenticationResponse).accessToken
        tokenHelperSoundcloud.saveToken(token)
    }

    override fun checkAuth(): Boolean {
        return tokenHelperSoundcloud.getToken() != null
    }
}
