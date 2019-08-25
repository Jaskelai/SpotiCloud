package com.github.jaskelai.spoticloud.ui.main.start.di

import com.github.jaskelai.spoticloud.ui.main.di.ScreenScope
import com.github.jaskelai.spoticloud.ui.main.start.StartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface StartBuilder {

    @ScreenScope
    @ContributesAndroidInjector(modules = [StartModule::class, SpotifyAuthModule::class])
    fun bindStartFragment(): StartFragment
}
