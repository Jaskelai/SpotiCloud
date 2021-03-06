package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.favtracks.di

import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.favtracks.FavTracksViewModel
import com.github.kornilovmikhail.spoticloud.ui.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FavTracksModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavTracksViewModel::class)
    fun provideTrackListViewModel(favTracksViewModel: FavTracksViewModel): ViewModel
}
