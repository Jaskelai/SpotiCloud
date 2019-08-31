package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LifecycleObserver
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.navigation.LocalBottomNavRouter
import javax.inject.Inject

class BottomNavContainerViewModel @Inject constructor(
    private val localBottomNavRouter: LocalBottomNavRouter
) : ViewModel(), LifecycleObserver {

    fun onSearchBottomBtnClicked() {

    }

    fun onTrackListBtnClicked() {
        localBottomNavRouter.navigateToTrackListScreen()
    }

    fun onTrendsBtnClicked() {
        localBottomNavRouter.navigateToTrendsScreen()
    }
}
