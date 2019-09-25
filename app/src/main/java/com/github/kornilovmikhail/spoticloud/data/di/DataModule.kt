package com.github.kornilovmikhail.spoticloud.data.di

import com.github.kornilovmikhail.spoticloud.data.local.di.RoomModule
import com.github.kornilovmikhail.spoticloud.data.local.di.SharedPreferencesModule
import com.github.kornilovmikhail.spoticloud.data.network.di.NetworkModule
import com.github.kornilovmikhail.spoticloud.data.network.di.TokenHelperModule
import com.github.kornilovmikhail.spoticloud.data.repository.di.RepositoryModule
import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        RepositoryModule::class,
        RoomModule::class,
        SharedPreferencesModule::class,
        TokenHelperModule::class
    ]
)
interface DataModule
