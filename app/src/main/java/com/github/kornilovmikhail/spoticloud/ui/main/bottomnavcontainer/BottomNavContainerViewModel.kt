package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.navigation.LocalBottomNavRouter
import javax.inject.Inject

class BottomNavContainerViewModel @Inject constructor(
    private val localBottomNavRouter: LocalBottomNavRouter
) : ViewModel(), LifecycleObserver {

    companion object {
        const val FAV_TRACKS_SCREEN = 0
        const val CURRENT_SCREEN = 1
    }

    val selectedItemLiveData = MutableLiveData<Int>()

    init {
        selectedItemLiveData.value = FAV_TRACKS_SCREEN
    }

    fun onSearchBottomBtnClicked() {
        localBottomNavRouter.navigateToSearchScreen()
        selectedItemLiveData.value = CURRENT_SCREEN
    }

    fun onTrackListBtnClicked() {
        localBottomNavRouter.navigateToTrackListScreen()
        selectedItemLiveData.value = CURRENT_SCREEN
    }

    fun onTrendsBtnClicked() {
        localBottomNavRouter.navigateToTrendsScreen()
        selectedItemLiveData.value = CURRENT_SCREEN
    }
}
