package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di

import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.di.BottomScreenScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsContainerFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.trendssoundcloud.di.TrendsSoundCloudFragmentBuilder
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.trendsspotify.di.TrendsSpotifyFragmentBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface TrendsContainerBuilder {

    @BottomScreenScope
    @ContributesAndroidInjector(
        modules = [
            TrendsSoundCloudFragmentBuilder::class,
            TrendsSpotifyFragmentBuilder::class,
            TrendsContainerModule::class]
    )
    fun bindTrendsListFragment(): TrendsContainerFragment
}
