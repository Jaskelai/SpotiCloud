package com.github.kornilovmikhail.spoticloud.ui.main.start.di

import android.net.Uri
import com.github.kornilovmikhail.spoticloud.BuildConfig
import com.github.kornilovmikhail.spoticloud.ui.main.di.ScreenScope
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SpotifyAuthModule {

    companion object {
        private const val CAMPAIGN = "your-campaign-token"
        private const val SCOPE_LIBRARY_READ = "user-library-read"
        private const val SCOPE_LIBRARY_MODIFY = "user-library-modify"
        private const val SCOPE_USER_READ_PRIVATE = "user-read-private"
        private const val SCOPE_STREAMING = "streaming"
        private const val SCHEME = "spotify-sdk"
        private const val AUTH = "auth"
    }

    @Provides
    @ScreenScope
    fun provideAuthenticationRequest(
        typeToken: AuthenticationResponse.Type,
        @Named("SPOTIFY_URI") redirectUri: Uri
    ): AuthenticationRequest {
        return AuthenticationRequest.Builder(
            BuildConfig.SPOTIFY_CLIENT_ID,
            typeToken,
            redirectUri.toString()
        )
            .setShowDialog(false)
            .setScopes(
                arrayOf(
                    SCOPE_LIBRARY_MODIFY,
                    SCOPE_USER_READ_PRIVATE,
                    SCOPE_LIBRARY_READ,
                    SCOPE_STREAMING
                )
            )
            .setCampaign(CAMPAIGN)
            .build()
    }

    @Provides
    @ScreenScope
    @Named("SPOTIFY_URI")
    fun provideRedirectUri(): Uri {
        return Uri.Builder()
            .scheme(SCHEME)
            .authority(AUTH)
            .build()
    }

    @Provides
    @ScreenScope
    fun provideTypeToken(): AuthenticationResponse.Type = AuthenticationResponse.Type.TOKEN
}
