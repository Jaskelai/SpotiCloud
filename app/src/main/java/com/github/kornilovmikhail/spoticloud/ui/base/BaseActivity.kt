package com.github.kornilovmikhail.spoticloud.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        injectViewModel()
        super.onCreate(savedInstanceState)
        subscribe()
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    protected fun inject() {
        AndroidInjection.inject(this)
    }

    protected abstract fun injectViewModel()

    protected abstract fun subscribe()
}
