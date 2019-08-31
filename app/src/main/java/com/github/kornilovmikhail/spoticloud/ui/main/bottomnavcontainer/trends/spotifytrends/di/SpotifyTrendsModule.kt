package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.spotifytrends.di

import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
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
        @SpotifyQualifier
        @JvmStatic
        fun provideTrendsSoundCloudAdapter(
            @SpotifyQualifier trackClickListener: TrackClickListener
        ): TrendsListAdapter {
            return TrendsListAdapter(trackClickListener)
        }
    }

    @Binds
    @TrendsScope
    @SpotifyQualifier
    abstract fun provideSpotifyTrendsClickListener(
        spotifyTrendsFragment: SpotifyTrendsFragment
    ): TrackClickListener
}
