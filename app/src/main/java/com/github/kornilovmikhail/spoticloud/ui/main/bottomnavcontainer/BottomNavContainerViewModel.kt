package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.Lifecycle
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.navigation.LocalBottomNavRouter
import javax.inject.Inject

class BottomNavContainerViewModel @Inject constructor(
    private val localRouter: LocalBottomNavRouter
) : ViewModel(), LifecycleObserver {

    companion object {
        const val SEARCH_ITEM = 0
        const val TRACK_LIST_ITEM = 1
        const val TRENDS_ITEM = 2
    }

    val selecteditemLiveData = MutableLiveData<Int>()

    private var isFirst: Boolean

    init {
        isFirst = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun navigateToTrackListScreen() {
        selecteditemLiveData.value = TRACK_LIST_ITEM
        localRouter.navigateToTrackListScreen()
        isFirst = false
    }

    fun onSearchBottomBtnClicked() {

    }

    fun onTrackListBtnClicked() {

    }

    fun onTrendsBtnClicked() {

    }
}
