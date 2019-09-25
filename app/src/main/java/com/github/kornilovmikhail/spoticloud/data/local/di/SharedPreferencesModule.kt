package com.github.kornilovmikhail.spoticloud.data.local.di

import android.content.Context
import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule {

    companion object {
        private const val NAME_SHAREDPREFS: String = "SPOTICLOUD_PREFERENCES"
    }

    @Provides
    @AppScope
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(NAME_SHAREDPREFS, Context.MODE_PRIVATE)
    }

    @Provides
    @AppScope
    fun provideRxSharedPreferences(sharedPreferences: SharedPreferences): RxSharedPreferences {
        return RxSharedPreferences.create(sharedPreferences)
    }

    @Provides
    @AppScope
    fun provideGson():Gson = Gson()
}
