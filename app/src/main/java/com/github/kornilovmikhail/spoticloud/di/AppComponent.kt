package com.github.kornilovmikhail.spoticloud.di

import android.content.Context
import com.github.kornilovmikhail.spoticloud.App
import com.github.kornilovmikhail.spoticloud.data.di.SharedPreferencesModule
import com.github.kornilovmikhail.spoticloud.data.di.NetworkModule
import com.github.kornilovmikhail.spoticloud.data.di.RepositoryModule
import com.github.kornilovmikhail.spoticloud.data.di.RoomModule
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import com.github.kornilovmikhail.spoticloud.ui.di.UIModule
import com.github.kornilovmikhail.spoticloud.ui.main.di.MainActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        RoomModule::class,
        SharedPreferencesModule::class,
        RepositoryModule::class,
        UIModule::class,
        MainActivityBuilder::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance appContext: Context): AppComponent
    }
}
