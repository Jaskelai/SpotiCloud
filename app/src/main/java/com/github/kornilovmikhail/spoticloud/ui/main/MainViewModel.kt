package com.github.kornilovmikhail.spoticloud.ui.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.Router
import javax.inject.Inject

class MainViewModel @Inject constructor(private val router: Router) : ViewModel(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun navigateToStartScreen() {
        router.navigateToStartScreen()
    }
}
