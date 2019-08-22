package com.github.kornilovmikhail.spoticloud.ui.main.feature.start.di

import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.di.ViewModelKey
import com.github.kornilovmikhail.spoticloud.ui.main.feature.start.StartViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface StartFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel::class)
    fun provideStartViewModel(startViewModel: StartViewModel): ViewModel
}
