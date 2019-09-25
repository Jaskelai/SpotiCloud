package com.github.kornilovmikhail.spoticloud.data.network.authenticator

import com.github.kornilovmikhail.spoticloud.data.network.di.NetworkModule
import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TokenHelper
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthenticatorSpotifyImpl @Inject constructor(
    @param:SpotifyQualifier private val tokenHelperSpotify: TokenHelper
): Authenticator {

    companion object {
        private const val HEADER_AUTHORIZATION = NetworkModule.AUTH_SPOTIFY_HEADER
        private const val HEADER_AUTHORIZATION_EXTRA = NetworkModule.AUTH_SPOTIFY_HEADER_EXTRA
    }

    @Synchronized
    override fun authenticate(route: Route?, response: Response): Request? {
        val storedToken = tokenHelperSpotify.getToken()
        val requestToken = response.request.header(HEADER_AUTHORIZATION)?.substringAfter(" ")

        val requestBuilder = response.request

        if (storedToken.equals(requestToken)) {
            tokenHelperSpotify.refresh()
        }

        return buildRequest(requestBuilder)
    }

    private fun buildRequest(request: Request): Request {
        var token: String? = ""
        if (request.header(HEADER_AUTHORIZATION) != null) {
            token = tokenHelperSpotify.getToken()
        }

        return request.newBuilder()
            .header(HEADER_AUTHORIZATION, "$HEADER_AUTHORIZATION_EXTRA $token")
            .build()
    }
}
