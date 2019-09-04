package com.github.kornilovmikhail.spoticloud.ui.main.player.di

import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.di.ViewModelKey
import com.github.kornilovmikhail.spoticloud.ui.main.player.PlayerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PlayerModule {

    @Binds
    @IntoMap
    @ViewModelKey(PlayerViewModel::class)
    fun providePlayerViewModel(playerViewModel: PlayerViewModel): ViewModel
}
