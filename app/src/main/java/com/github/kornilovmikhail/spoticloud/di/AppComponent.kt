package com.github.kornilovmikhail.spoticloud.di

import android.content.Context
import com.github.kornilovmikhail.spoticloud.App
import com.github.kornilovmikhail.spoticloud.data.di.LocalDataModule
import com.github.kornilovmikhail.spoticloud.data.di.NetworkModule
import com.github.kornilovmikhail.spoticloud.data.di.RepositoryModule
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import com.github.kornilovmikhail.spoticloud.ui.main.di.MainActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        CiceroneModule::class,
        NetworkModule::class,
        LocalDataModule::class,
        RepositoryModule::class,
        MainActivityBuilder::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance appContext: Context): AppComponent
    }
}
