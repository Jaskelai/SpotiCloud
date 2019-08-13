package com.github.kornilovmikhail.spoticloud_mvvm

import android.app.Application
import com.github.kornilovmikhail.spoticloud_mvvm.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .factory()
            .create(applicationContext)
    }
}
