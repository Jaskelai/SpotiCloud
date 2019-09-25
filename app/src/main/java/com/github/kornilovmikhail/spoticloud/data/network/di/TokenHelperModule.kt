package com.github.kornilovmikhail.spoticloud.data.network.di

import com.github.kornilovmikhail.spoticloud.data.network.tokenhelper.TokenHelperSoundcloudImpl
import com.github.kornilovmikhail.spoticloud.data.network.tokenhelper.TokenHelperSpotifyImpl
import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TokenHelper
import dagger.Binds
import dagger.Module

@Module
interface TokenHelperModule {

    @Binds
    @AppScope
    @SoundCloudQualifier
    fun provideTokenHelperSoundcloud(tokenHelperSoundcloud: TokenHelperSoundcloudImpl): TokenHelper

    @Binds
    @AppScope
    @SpotifyQualifier
    fun provideTokenHelperSpotify(tokenHelperSpotify: TokenHelperSpotifyImpl): TokenHelper
}
