package com.github.kornilovmikhail.spoticloud.data.di

import android.content.Context
import android.content.SharedPreferences
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
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
}
