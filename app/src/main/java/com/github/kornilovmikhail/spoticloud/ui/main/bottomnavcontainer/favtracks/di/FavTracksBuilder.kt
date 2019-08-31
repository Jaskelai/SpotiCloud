package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.favtracks.di

import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.di.BottomScreenScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.favtracks.FavTracksFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FavTracksBuilder {

    @BottomScreenScope
    @ContributesAndroidInjector(modules = [FavTracksModule::class])
    fun bindTrackListFragment(): FavTracksFragment
}
