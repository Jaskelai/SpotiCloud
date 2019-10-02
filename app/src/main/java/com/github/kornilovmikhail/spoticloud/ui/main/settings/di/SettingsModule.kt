package com.github.kornilovmikhail.spoticloud.ui.main.settings.di

import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.di.ViewModelKey
import com.github.kornilovmikhail.spoticloud.ui.main.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SettingsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun provideSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel
}
