package com.github.kornilovmikhail.spoticloud.ui.main.feature.soundcloudauth.di

import com.github.kornilovmikhail.spoticloud.ui.main.feature.di.ScreenScope
import com.github.kornilovmikhail.spoticloud.ui.main.feature.soundcloudauth.SoundcloudAuthFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SoundcloudAuthFragmentBuilder {

    @ScreenScope
    @ContributesAndroidInjector(modules = [SoundcloudAuthFragmentModule::class])
    fun bindSoundcloudAuthFragment(): SoundcloudAuthFragment
}
