package com.github.kornilovmikhail.spoticloud.ui.main.di

import androidx.lifecycle.ViewModelProvider
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.Router
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.RouterCiceroneImpl
import dagger.Binds
import dagger.Module

@Module
interface MainActivityModule {

    @Binds
    @MainActivityScope
    fun provideRouter(router: RouterCiceroneImpl): Router

    @Binds
    @MainActivityScope
    fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
