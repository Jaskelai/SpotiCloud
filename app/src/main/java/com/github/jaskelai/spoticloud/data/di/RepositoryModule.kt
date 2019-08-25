package com.github.jaskelai.spoticloud.data.di

import com.github.jaskelai.spoticloud.data.repository.CommonUserRepositoryImpl
import com.github.jaskelai.spoticloud.data.repository.UserSoundcloudRepositoryImpl
import com.github.jaskelai.spoticloud.data.repository.UserSpotifyRepositoryImpl
import com.github.jaskelai.spoticloud.di.scope.AppScope
import com.github.jaskelai.spoticloud.domain.interfaces.CommonUserRepository
import com.github.jaskelai.spoticloud.domain.interfaces.UserSoundcloudRepository
import com.github.jaskelai.spoticloud.domain.interfaces.UserSpotifyRepository
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
}
