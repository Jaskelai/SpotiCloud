package com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer.di

import com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer.BottomNavContainerFragment
import com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer.tracklist.di.TrackListBuilder
import com.github.jaskelai.spoticloud.ui.main.di.ScreenScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface BottomNavContainerBuilder {

    @ScreenScope
    @ContributesAndroidInjector(
        modules = [
            TrackListBuilder::class,
            BottomNavContainerModule::class
        ]
    )
    fun bindBottomNavContainerFragment(): BottomNavContainerFragment
}
