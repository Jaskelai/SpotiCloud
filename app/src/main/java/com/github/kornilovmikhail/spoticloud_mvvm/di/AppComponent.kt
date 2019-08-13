package com.github.kornilovmikhail.spoticloud_mvvm.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@AppScope
@Component(modules = [AndroidInjectionModule::class, AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{

        fun create(@BindsInstance appContext: Context): AppComponent
    }
}
