package com.github.kornilovmikhail.spoticloud.ui.main.di

import com.github.kornilovmikhail.spoticloud.ui.main.MainActivity
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.di.BottomNavContainerBuilder
import com.github.kornilovmikhail.spoticloud.ui.main.player.di.PlayerBuilder
import com.github.kornilovmikhail.spoticloud.ui.main.settings.di.SettingsBuilder
import com.github.kornilovmikhail.spoticloud.ui.main.soundcloudauth.di.SoundcloudAuthBuilder
import com.github.kornilovmikhail.spoticloud.ui.main.spotifyauth.di.SpotifyAuthBuilder
import com.github.kornilovmikhail.spoticloud.ui.main.start.di.StartBuilder
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
            SpotifyAuthBuilder::class,
            PlayerBuilder::class,
            BottomNavContainerBuilder::class,
            SettingsBuilder::class
        ]
    )
    fun bindMainActivity(): MainActivity
}
