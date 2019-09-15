package com.github.kornilovmikhail.spoticloud.ui.main

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.domain.interactors.CommonAuthUseCase
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.GlobalRouter
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val globalRouter: GlobalRouter,
    private val commonAuthUseCase: CommonAuthUseCase
) : ViewModel(), LifecycleObserver {

    init {
        navigateToStartScreen()
    }

    private fun navigateToStartScreen() {
        if (commonAuthUseCase.isAuthed()) {
            globalRouter.navigateToBottomNavScreen()
        } else {
            globalRouter.navigateToStartScreen()
        }
    }
}
