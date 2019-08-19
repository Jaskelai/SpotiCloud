package com.github.kornilovmikhail.spoticloud.data.di

import com.github.kornilovmikhail.spoticloud.data.repository.UserSoundcloudRepositoryImpl
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import com.github.kornilovmikhail.spoticloud.domain.interfaces.UserSoundcloudRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    @AppScope
    fun provideUserSoundcloudRepository(
        userSoundcloudRepositoryImpl: UserSoundcloudRepositoryImpl
    ): UserSoundcloudRepository
}
