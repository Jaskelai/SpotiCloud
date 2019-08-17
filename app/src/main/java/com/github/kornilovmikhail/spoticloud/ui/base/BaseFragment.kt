package com.github.kornilovmikhail.spoticloud.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment: Fragment(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    protected fun inject() {
        AndroidSupportInjection.inject(this)
    }

    protected abstract fun subscribe()
}
