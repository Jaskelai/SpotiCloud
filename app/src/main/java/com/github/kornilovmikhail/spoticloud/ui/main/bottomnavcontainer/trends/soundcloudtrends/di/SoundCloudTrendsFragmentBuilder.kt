package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.soundcloudtrends.di

import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di.TrendsScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.soundcloudtrends.SoundCloudTrendsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SoundCloudTrendsFragmentBuilder {

    @TrendsScope
    @ContributesAndroidInjector(modules = [SoundCloudTrendsModule::class])
    fun bindSoundCloudTrendsFragment(): SoundCloudTrendsFragment
}
