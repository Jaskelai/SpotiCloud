package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.spotifytrends.di

import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.TrackClickListener
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsListAdapter
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di.TrendsScope
import dagger.Module
import dagger.Provides

@Module
class SpotifyTrendsModule {

    @Provides
    @TrendsScope
    @SpotifyQualifier
    fun provideSpotofyTrendsAdapter(
        trackClickListener: TrackClickListener
    ): TrendsListAdapter = TrendsListAdapter(trackClickListener)
}
