package com.github.kornilovmikhail.spoticloud_mvvm.di

import android.content.Context
import com.github.kornilovmikhail.spoticloud_mvvm.App
import com.github.kornilovmikhail.spoticloud_mvvm.di.scope.AppScope
import com.github.kornilovmikhail.spoticloud_mvvm.ui.main.di.MainActivityBuilder
import com.github.kornilovmikhail.spoticloud_mvvm.ui.main.di.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(modules = [AndroidInjectionModule::class, MainActivityBuilder::class])
interface AppComponent: AndroidInjector<App> {

    @Component.Factory
    interface Factory{

        fun create(@BindsInstance appContext: Context): AppComponent
    }
}
