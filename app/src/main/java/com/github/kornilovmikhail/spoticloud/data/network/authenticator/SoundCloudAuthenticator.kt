package com.github.kornilovmikhail.spoticloud.data.network.authenticator

import com.github.kornilovmikhail.spoticloud.data.network.tokenhelper.TokenHelperSoundcloud
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

@AppScope
class SoundCloudAuthenticator @Inject constructor(
    private val tokenHelperSoundcloud: TokenHelperSoundcloud
) : Authenticator {

    companion object {
        private const val TOKEN_QUERY = "oauth_token"
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
