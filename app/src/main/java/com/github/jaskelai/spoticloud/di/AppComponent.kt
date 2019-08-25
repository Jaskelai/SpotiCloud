package com.github.jaskelai.spoticloud.di

import android.content.Context
import com.github.jaskelai.spoticloud.App
import com.github.jaskelai.spoticloud.data.di.SharedPreferencesModule
import com.github.jaskelai.spoticloud.data.di.NetworkModule
import com.github.jaskelai.spoticloud.data.di.RepositoryModule
import com.github.jaskelai.spoticloud.di.scope.AppScope
import com.github.jaskelai.spoticloud.ui.main.di.MainActivityBuilder
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
        SharedPreferencesModule::class,
        RepositoryModule::class,
        MainActivityBuilder::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance appContext: Context): AppComponent
    }
}
