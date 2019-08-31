package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.spotifytrends.di

import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di.TrendsScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.spotifytrends.SpotifyTrendsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SpotifyTrendsFragmentBuilder {

    @TrendsScope
    @ContributesAndroidInjector(modules = [SpotifyTrendsModule::class])
    fun bindSpotifyTrendsFragment(): SpotifyTrendsFragment
}
