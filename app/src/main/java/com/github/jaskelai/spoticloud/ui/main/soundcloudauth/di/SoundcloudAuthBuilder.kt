package com.github.jaskelai.spoticloud.ui.main.soundcloudauth.di

import com.github.jaskelai.spoticloud.ui.main.di.ScreenScope
import com.github.jaskelai.spoticloud.ui.main.soundcloudauth.SoundcloudAuthFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SoundcloudAuthBuilder {

    @ScreenScope
    @ContributesAndroidInjector(modules = [SoundcloudAuthModule::class])
    fun bindSoundcloudAuthFragment(): SoundcloudAuthFragment
}
