package com.github.kornilovmikhail.spoticloud.data.network.di

import com.github.kornilovmikhail.spoticloud.data.network.authenticator.AuthenticatorSoundcloudImpl
import com.github.kornilovmikhail.spoticloud.data.network.authenticator.AuthenticatorSpotifyImpl
import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import dagger.Binds
import dagger.Module
import okhttp3.Authenticator

@Module
interface AuthenticatorModule {

    @Binds
    @AppScope
    @SoundCloudQualifier
    fun provideSoundCloudAuthenticator(soundCloudAuthenticator: AuthenticatorSoundcloudImpl): Authenticator

    @Binds
    @AppScope
    @SpotifyQualifier
    fun provideSpotifyAuthenticator(spotifyAuthenticator: AuthenticatorSpotifyImpl): Authenticator
}
