package com.github.kornilovmikhail.spoticloud.ui.main.soundcloudauth.di

import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.di.ViewModelKey
import com.github.kornilovmikhail.spoticloud.ui.main.soundcloudauth.SoundcloudAuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SoundcloudAuthModule {

    @Binds
    @IntoMap
    @ViewModelKey(SoundcloudAuthViewModel::class)
    fun provideSoundcloudAuthViewModel(soundcloudAuthViewModel: SoundcloudAuthViewModel): ViewModel
}
