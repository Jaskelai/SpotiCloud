package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di

import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.di.BottomScreenScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsContainerFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.soundcloudtrends.di.SoundCloudTrendsFragmentBuilder
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.spotifytrends.di.SpotifyTrendsFragmentBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface TrendsContainerBuilder {

    @BottomScreenScope
    @ContributesAndroidInjector(
        modules = [
            SoundCloudTrendsFragmentBuilder::class,
            SpotifyTrendsFragmentBuilder::class,
            TrendsContainerModule::class]
    )
    fun bindTrendsListFragment(): TrendsContainerFragment
}
