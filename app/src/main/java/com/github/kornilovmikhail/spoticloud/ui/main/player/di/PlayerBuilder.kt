package com.github.kornilovmikhail.spoticloud.ui.main.player.di

import com.github.kornilovmikhail.spoticloud.ui.main.di.ScreenScope
import com.github.kornilovmikhail.spoticloud.ui.main.player.PlayerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface PlayerBuilder {

    @ScreenScope
    @ContributesAndroidInjector(modules = [PlayerModule::class])
    fun bindPlayerFragment(): PlayerFragment
}
