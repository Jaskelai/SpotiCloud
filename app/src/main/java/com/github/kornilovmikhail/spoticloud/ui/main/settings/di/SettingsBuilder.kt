package com.github.kornilovmikhail.spoticloud.ui.main.settings.di

import com.github.kornilovmikhail.spoticloud.ui.main.di.ScreenScope
import com.github.kornilovmikhail.spoticloud.ui.main.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SettingsBuilder {

    @ScreenScope
    @ContributesAndroidInjector(modules = [SettingsModule::class])
    fun bindSettingsFragment(): SettingsFragment
}
