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
        private const val TOKEN_HEADER = NetworkModule.AUTH_SPOTIFY_HEADER
        private const val TOKEN_HEADER_EXTRA = NetworkModule.AUTH_SPOTIFY_HEADER_EXTRA
    }

    @Synchronized
    override fun authenticate(route: Route?, response: Response): Request? {
        val storedToken = tokenHelperSpotify.getToken()
        val requestToken = response.request.header(TOKEN_HEADER)?.substringAfter(" ")

        val requestBuilder = response.request

        if (storedToken.equals(requestToken)) {
            tokenHelperSpotify.refresh()
        }

        return buildRequest(requestBuilder)
    }

    private fun buildRequest(request: Request): Request {
        var token: String? = ""
        if (request.header(TOKEN_HEADER) != null) {
            token = tokenHelperSpotify.getToken()
        }

        return request.newBuilder()
            .header(TOKEN_HEADER, "$TOKEN_HEADER_EXTRA $token")
            .build()
    }
}
