package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.trendssoundcloud.di

import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.ui.di.ViewModelKey
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.TrackClickListener
import com.github.kornilovmikhail.spoticloud.domain.interfaces.SearchTrendsPopupMenuDelegate
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsListAdapter
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di.TrendsScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.trendssoundcloud.TrendsSoundCloudViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class TrendsSoundCloudModule {

    @Module
    companion object {

        @Provides
        @TrendsScope
        @SoundCloudQualifier
        @JvmStatic
        fun provideSoundCloudTrendsAdapter(
            trackClickListener: TrackClickListener,
            popupMenuDelegate: SearchTrendsPopupMenuDelegate
        ): TrendsListAdapter = TrendsListAdapter(trackClickListener, popupMenuDelegate)
    }

    @Binds
    @IntoMap
    @ViewModelKey(TrendsSoundCloudViewModel::class)
    abstract fun provideTrendsSoundCloudViewModel(
        trendsSoundCloudViewModel: TrendsSoundCloudViewModel
    ): ViewModel
}
