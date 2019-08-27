package com.github.kornilovmikhail.spoticloud.ui.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.domain.interactors.CommonAuthUseCase
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.GlobalRouter
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val globalRouter: GlobalRouter,
    private val commonAuthUseCase: CommonAuthUseCase
) : ViewModel(), LifecycleObserver {

    private var isFirst = true

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun navigateToStartScreen() {
        if (isFirst) {
            if (commonAuthUseCase.checkAuth()) {
                globalRouter.navigateToBottomNavScreen()
            } else {
                globalRouter.navigateToStartScreen()
            }
            isFirst = false
        }
    }
}
