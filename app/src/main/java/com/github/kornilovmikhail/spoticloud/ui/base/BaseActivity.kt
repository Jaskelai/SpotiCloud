package com.github.kornilovmikhail.spoticloud.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        subscribe()
    }

    protected abstract fun inject()

    protected abstract fun subscribe()
}
