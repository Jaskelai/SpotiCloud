package com.github.kornilovmikhail.spoticloud.ui.main.di

import com.github.kornilovmikhail.spoticloud.ui.main.MainActivity
import com.github.kornilovmikhail.spoticloud.ui.main.feature.soundcloudauth.di.SoundcloudAuthFragmentBuilder
import com.github.kornilovmikhail.spoticloud.ui.main.feature.start.di.StartFragmentBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityBuilder {

    @MainActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
            StartFragmentBuilder::class,
            SoundcloudAuthFragmentBuilder::class]
    )
    fun bindMainActivity(): MainActivity
}
