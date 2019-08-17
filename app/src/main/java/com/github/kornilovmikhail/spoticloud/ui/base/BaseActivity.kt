package com.github.kornilovmikhail.spoticloud.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        subscribe()
    }

    protected fun inject() {
        AndroidInjection.inject(this)
    }

    protected abstract fun subscribe()
}
