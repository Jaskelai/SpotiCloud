package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.trendsspotify.di

import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di.TrendsScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.trendsspotify.TrendsSpotifyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface TrendsSpotifyFragmentBuilder {

    @TrendsScope
    @ContributesAndroidInjector(modules = [TrendsSpotifyModule::class])
    fun bindSpotifyTrendsFragment(): TrendsSpotifyFragment
}
