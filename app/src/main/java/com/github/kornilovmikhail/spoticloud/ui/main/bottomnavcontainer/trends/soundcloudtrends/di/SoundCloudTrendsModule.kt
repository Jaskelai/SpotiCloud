package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.soundcloudtrends.di

import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.TrackClickListener
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsListAdapter
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di.TrendsScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.soundcloudtrends.SoundCloudTrendsFragment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class SoundCloudTrendsModule {

    @Module
    companion object {

        @Provides
        @TrendsScope
        @SoundCloudTrendsQualifier
        @JvmStatic
        fun provideTrendsSoundCloudAdapter(
            @SoundCloudTrendsQualifier trackClickListener: TrackClickListener
        ): TrendsListAdapter {
            return TrendsListAdapter(trackClickListener)
        }
    }

    @Binds
    @TrendsScope
    @SoundCloudTrendsQualifier
    abstract fun provideSoundCloudTrendsClickListener(
        soundCloudTrendsFragment: SoundCloudTrendsFragment
    ): TrackClickListener
}
