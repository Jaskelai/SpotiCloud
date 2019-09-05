package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.soundcloudtrends.di

import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.TrackClickListener
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsListAdapter
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di.TrendsScope
import dagger.Module
import dagger.Provides

@Module
class SoundCloudTrendsModule {

    @Provides
    @TrendsScope
    @SoundCloudQualifier
    fun provideSoundCloudTrendsAdapter(
        trackClickListener: TrackClickListener
    ): TrendsListAdapter = TrendsListAdapter(trackClickListener)
}
