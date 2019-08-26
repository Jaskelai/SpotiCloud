package com.github.jaskelai.spoticloud.ui.main.di

import com.github.jaskelai.spoticloud.ui.main.MainActivity
import com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer.di.BottomNavContainerBuilder
import com.github.jaskelai.spoticloud.ui.main.soundcloudauth.di.SoundcloudAuthBuilder
import com.github.jaskelai.spoticloud.ui.main.start.di.StartBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityBuilder {

    @MainActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
            StartBuilder::class,
            SoundcloudAuthBuilder::class,
            BottomNavContainerBuilder::class]
    )
    fun bindMainActivity(): MainActivity
}