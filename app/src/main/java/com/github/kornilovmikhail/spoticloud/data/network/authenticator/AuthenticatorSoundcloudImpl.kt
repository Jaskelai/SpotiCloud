package com.github.kornilovmikhail.spoticloud.data.network.authenticator

import com.github.kornilovmikhail.spoticloud.data.network.di.NetworkModule
import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TokenHelper
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthenticatorSoundcloudImpl @Inject constructor(
    @param: SoundCloudQualifier private val tokenHelperSoundcloud: TokenHelper
) : Authenticator {

    companion object {
        private const val TOKEN_QUERY = NetworkModule.AUTH_SOUNDCLOUD_QUERY
    }

    @Synchronized
    override fun authenticate(route: Route?, response: Response): Request? {
        val storedToken = tokenHelperSoundcloud.getToken()
        val requestToken = response.request.url.queryParameter(TOKEN_QUERY)

        val requestBuilder = response.request

        if (storedToken.equals(requestToken)) {
            tokenHelperSoundcloud.refresh()
        }

        return buildRequest(requestBuilder)
    }

    private fun buildRequest(request: Request): Request {
        var url = request.url

        if (url.queryParameter(TOKEN_QUERY) != null) {
            url = url.newBuilder()
                .addQueryParameter(TOKEN_QUERY, tokenHelperSoundcloud.getToken())
                .build()
        }

        return request.newBuilder()
            .url(url)
            .build()
    }
}
