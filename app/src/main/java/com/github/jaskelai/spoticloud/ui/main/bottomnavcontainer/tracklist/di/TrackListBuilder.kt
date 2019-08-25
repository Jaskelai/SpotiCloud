package com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer.tracklist.di

import com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer.di.BottomScreenScope
import com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer.tracklist.TrackListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface TrackListBuilder {

    @BottomScreenScope
    @ContributesAndroidInjector(modules = [TrackListModule::class])
    fun bindTrackListFragment(): TrackListFragment
}
