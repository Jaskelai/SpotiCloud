package com.github.kornilovmikhail.spoticloud.ui.main.start.di

import com.github.kornilovmikhail.spoticloud.ui.main.di.ScreenScope
import com.github.kornilovmikhail.spoticloud.ui.main.start.StartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface StartBuilder {

    @ScreenScope
    @ContributesAndroidInjector(modules = [StartModule::class])
    fun bindStartFragment(): StartFragment
}
