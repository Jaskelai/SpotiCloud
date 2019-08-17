package com.github.kornilovmikhail.spoticloud.ui.feature.start.di

import com.github.kornilovmikhail.spoticloud.ui.feature.di.ScreenScope
import com.github.kornilovmikhail.spoticloud.ui.feature.start.StartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface StartFragmentBuilder {

    @ScreenScope
    @ContributesAndroidInjector
    fun bindStartFragment(): StartFragment
}
