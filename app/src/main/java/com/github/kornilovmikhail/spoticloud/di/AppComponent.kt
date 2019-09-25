package com.github.kornilovmikhail.spoticloud.di

import android.content.Context
import com.github.kornilovmikhail.spoticloud.App
import com.github.kornilovmikhail.spoticloud.data.di.DataModule
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import com.github.kornilovmikhail.spoticloud.ui.di.UIModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        DataModule::class,
        UIModule::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance appContext: Context): AppComponent
    }
}
