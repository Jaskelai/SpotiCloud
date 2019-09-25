package com.github.kornilovmikhail.spoticloud.data.network.authenticator

import com.github.kornilovmikhail.spoticloud.data.network.tokenhelper.TokenHelperSoundcloudImpl
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

@AppScope
class SoundCloudAuthenticator @Inject constructor(
    private val tokenHelperSoundcloudImpl: TokenHelperSoundcloudImpl
) : Authenticator {

    companion object {
        private const val TOKEN_QUERY = "oauth_token"
    }

    @Synchronized
    override fun authenticate(route: Route?, response: Response): Request? {
        val storedToken = tokenHelperSoundcloudImpl.getToken()
        val requestToken = response.request.url.queryParameter(TOKEN_QUERY)

        val requestBuilder = response.request

        if (storedToken.equals(requestToken)) {
            tokenHelperSoundcloudImpl.refresh()
        }

        return buildRequest(requestBuilder)
    }

    private fun buildRequest(request: Request): Request {
        var url = request.url

        if (url.queryParameter(TOKEN_QUERY) != null) {
            url = url.newBuilder()
                .addQueryParameter(TOKEN_QUERY, tokenHelperSoundcloudImpl.getToken())
                .build()
        }

        return request.newBuilder()
            .url(url)
            .build()
    }
}
