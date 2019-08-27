package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.tracklist.di

import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.di.BottomScreenScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.tracklist.TrackListClickListener
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.tracklist.TrackListFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.tracklist.TrackListViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface TrackListModule {

    @Binds
    @IntoMap
    @ViewModelKey(TrackListViewModel::class)
    fun provideTrackListViewModel(trackListViewModel: TrackListViewModel): ViewModel

    @Binds
    @BottomScreenScope
    fun provideTrackListClickListener(trackListFragment: TrackListFragment): TrackListClickListener
}
