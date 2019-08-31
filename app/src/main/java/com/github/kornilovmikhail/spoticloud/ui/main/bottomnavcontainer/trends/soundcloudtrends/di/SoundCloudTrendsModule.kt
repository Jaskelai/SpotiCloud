package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.soundcloudtrends.di

import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
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
        @SoundCloudQualifier
        @JvmStatic
        fun provideTrendsSoundCloudAdapter(
            @SoundCloudQualifier trackClickListener: TrackClickListener
        ): TrendsListAdapter {
            return TrendsListAdapter(trackClickListener)
        }
    }

    @Binds
    @TrendsScope
    @SoundCloudQualifier
    abstract fun provideSoundCloudTrendsClickListener(
        soundCloudTrendsFragment: SoundCloudTrendsFragment
    ): TrackClickListener
}
