package com.github.kornilovmikhail.spoticloud.data.repository.di

import com.github.kornilovmikhail.spoticloud.data.repository.*
import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.*
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    @AppScope
    fun provideUserSoundcloudRepository(
        userSoundcloudRepositoryImpl: UserSoundcloudRepositoryImpl
    ): UserSoundcloudRepository

    @Binds
    @AppScope
    fun provideUserSpotifyRepository(
        userSpotifyRepositoryImpl: UserSpotifyRepositoryImpl
    ): UserSpotifyRepository

    @Binds
    @AppScope
    fun provideUserCommonRepository(
        userCommonRepository: CommonUserRepositoryImpl
    ): CommonUserRepository

    @Binds
    @AppScope
    @SoundCloudQualifier
    fun provideSoundCloudFavTracksRepository(
        tracksSoundcloudRepository: TracksRepositorySoundcloudImpl
    ): TracksRepository

    @Binds
    @AppScope
    @SpotifyQualifier
    fun provideSpotifyFavTracksRepository(
        tracksSpotifyRepository: TracksRepositorySpotifyImpl
    ): TracksRepository

    @Binds
    @AppScope
    fun provideCurrentTrackRepository(
        currentTrackRepositoryImpl: CurrentTrackRepositoryImpl
    ): CurrentTrackRepository
}
