package com.github.kornilovmikhail.spoticloud.ui.main.feature.start.di

import com.github.kornilovmikhail.spoticloud.ui.main.feature.di.ScreenScope
import com.github.kornilovmikhail.spoticloud.ui.main.feature.start.StartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface StartFragmentBuilder {

    @ScreenScope
    @ContributesAndroidInjector(modules = [StartFragmentModule::class])
    fun bindStartFragment(): StartFragment
}
