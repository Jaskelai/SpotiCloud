package com.github.kornilovmikhail.spoticloud.ui.main.spotifyauth.di

import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.di.ViewModelKey
import com.github.kornilovmikhail.spoticloud.ui.main.spotifyauth.SpotifyAuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SpotifyAuthModule {

    @Binds
    @IntoMap
    @ViewModelKey(SpotifyAuthViewModel::class)
    fun provideSpotifyAuthViewModel(spotifyAuthViewModel: SpotifyAuthViewModel): ViewModel
}
