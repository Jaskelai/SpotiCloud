package com.github.kornilovmikhail.spoticloud.ui.main.spotifyauth.di

import com.github.kornilovmikhail.spoticloud.ui.main.di.ScreenScope
import com.github.kornilovmikhail.spoticloud.ui.main.spotifyauth.SpotifyAuthFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SpotifyAuthBuilder {

    @ScreenScope
    @ContributesAndroidInjector(modules = [SpotifyAuthModule::class])
    fun bindSpotifyAuthFragment(): SpotifyAuthFragment
}
