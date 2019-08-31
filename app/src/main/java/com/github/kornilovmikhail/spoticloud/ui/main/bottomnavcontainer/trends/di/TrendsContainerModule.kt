package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di

import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsContainerViewModel
import com.github.kornilovmikhail.spoticloud.ui.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TrendsContainerModule {

    @Binds
    @IntoMap
    @ViewModelKey(TrendsContainerViewModel::class)
    abstract fun provideTrendsContainerViewModel(trackContainerViewModel: TrendsContainerViewModel): ViewModel
}
