package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.di

import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.BottomNavContainerViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BottomNavContainerModule {

    @Binds
    @IntoMap
    @ViewModelKey(BottomNavContainerViewModel::class)
    fun provideStartViewModel(bottomNavContainerViewModel: BottomNavContainerViewModel): ViewModel
}
