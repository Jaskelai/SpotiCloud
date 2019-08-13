package com.github.kornilovmikhail.spoticloud_mvvm.ui.main.di

import com.github.kornilovmikhail.spoticloud_mvvm.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityBuilder {

    @ContributesAndroidInjector
    @MainActivityScope
    fun bindMainActivity(): MainActivity
}
