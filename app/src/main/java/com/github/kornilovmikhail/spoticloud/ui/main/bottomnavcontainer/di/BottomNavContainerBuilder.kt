package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.di

import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.BottomNavContainerFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.tracklist.di.TrackListBuilder
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di.TrendsContainerBuilder
import com.github.kornilovmikhail.spoticloud.ui.main.di.ScreenScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface BottomNavContainerBuilder {

    @ScreenScope
    @ContributesAndroidInjector(
        modules = [
            TrackListBuilder::class,
            TrendsContainerBuilder::class,
            BottomNavContainerModule::class
        ]
    )
    fun bindBottomNavContainerFragment(): BottomNavContainerFragment
}
