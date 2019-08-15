package com.github.kornilovmikhail.spoticloud.ui.main.di

import com.github.kornilovmikhail.spoticloud.ui.main.MainActivity
import com.github.kornilovmikhail.spoticloud.ui.navigation.di.CiceroneModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityBuilder {

    @MainActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class, CiceroneModule::class])
    fun bindMainActivity(): MainActivity
}
