package com.github.kornilovmikhail.spoticloud.ui.main.di

import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.MainViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.feature.soundcloudauth.SoundcloudAuthViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.feature.start.StartViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel::class)
    fun provideStartViewModel(startViewModel: StartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SoundcloudAuthViewModel::class)
    fun provideSoundcloudAuthViewModel(soundcloudAuthViewModel: SoundcloudAuthViewModel): ViewModel
}
