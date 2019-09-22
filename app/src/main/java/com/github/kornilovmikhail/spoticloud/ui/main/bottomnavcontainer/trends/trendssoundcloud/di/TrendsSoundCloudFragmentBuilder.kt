package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.trendssoundcloud.di

import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di.TrendsScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.trendssoundcloud.TrendsSoundCloudFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface TrendsSoundCloudFragmentBuilder {

    @TrendsScope
    @ContributesAndroidInjector(modules = [TrendsSoundCloudModule::class])
    fun bindSoundCloudTrendsFragment(): TrendsSoundCloudFragment
}
