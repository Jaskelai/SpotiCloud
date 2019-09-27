package com.github.kornilovmikhail.spoticloud.data.local.di

import android.content.Context
import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.github.kornilovmikhail.spoticloud.data.local.sharedprefs.SharedPreferencesStorageImpl
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import com.github.kornilovmikhail.spoticloud.domain.interfaces.SharedPreferencesStorage
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class SharedPreferencesModule {

    @Module
    companion object {
        private const val NAME_SHAREDPREFS: String = "SPOTICLOUD_PREFERENCES"

        @Provides
        @AppScope
        @JvmStatic
        fun provideSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(NAME_SHAREDPREFS, Context.MODE_PRIVATE)
        }

        @Provides
        @AppScope
        @JvmStatic
        fun provideRxSharedPreferences(sharedPreferences: SharedPreferences): RxSharedPreferences {
            return RxSharedPreferences.create(sharedPreferences)
        }

        @Provides
        @AppScope
        @JvmStatic
        fun provideGson(): Gson = Gson()
    }

    @Binds
    @AppScope
    abstract fun provideSharedPreferencesStorage(
        sharedPreferencesStorageImpl: SharedPreferencesStorageImpl
    ): SharedPreferencesStorage
}
