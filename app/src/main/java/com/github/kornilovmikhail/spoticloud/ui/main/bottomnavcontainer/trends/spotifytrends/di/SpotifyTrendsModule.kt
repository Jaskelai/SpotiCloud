package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.spotifytrends.di

import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.TrackClickListener
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsListAdapter
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di.TrendsScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.spotifytrends.SpotifyTrendsFragment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class SpotifyTrendsModule {

    @Module
    companion object {

        @Provides
        @TrendsScope
        @SpotifyTrendsQualifier
        @JvmStatic
        fun provideTrendsSoundCloudAdapter(
            @SpotifyTrendsQualifier trackClickListener: TrackClickListener
        ): TrendsListAdapter {
            return TrendsListAdapter(trackClickListener)
        }
    }

    @Binds
    @TrendsScope
    @SpotifyTrendsQualifier
    abstract fun provideSpotifyTrendsClickListener(
        spotifyTrendsFragment: SpotifyTrendsFragment
    ): TrackClickListener
}
