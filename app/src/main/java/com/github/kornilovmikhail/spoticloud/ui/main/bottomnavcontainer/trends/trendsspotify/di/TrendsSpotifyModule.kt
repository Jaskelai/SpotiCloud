package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.trendsspotify.di

import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.ui.di.ViewModelKey
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.TrackClickListener
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.popupmenu.PopupMenuDelegate
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsListAdapter
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di.TrendsScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.trendsspotify.TrendsSpotifyViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class TrendsSpotifyModule {

    @Module
    companion object {

        @Provides
        @TrendsScope
        @SpotifyQualifier
        @JvmStatic
        fun provideSpotofyTrendsAdapter(
            trackClickListener: TrackClickListener,
            popupMenuDelegate: PopupMenuDelegate
        ): TrendsListAdapter = TrendsListAdapter(trackClickListener, popupMenuDelegate)
    }

    @Binds
    @IntoMap
    @ViewModelKey(TrendsSpotifyViewModel::class)
    abstract fun provideTrendsSpotifyViewModel(
        trendsSpotifyViewModel: TrendsSpotifyViewModel
    ): ViewModel
}
