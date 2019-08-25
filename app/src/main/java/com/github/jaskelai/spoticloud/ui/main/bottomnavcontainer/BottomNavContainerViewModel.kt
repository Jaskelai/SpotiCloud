package com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer

import androidx.lifecycle.*
import com.github.jaskelai.spoticloud.ui.navigation.router.Router
import javax.inject.Inject

class BottomNavContainerViewModel @Inject constructor(
    private val router: Router
) : ViewModel(), LifecycleObserver {

    companion object {
        const val SEARCH_ITEM = 0
        const val TRACK_LIST_ITEM = 1
        const val TRENDS_ITEM = 2
    }

    val selecteditemLiveData = MutableLiveData<Int>()

    init {
        selecteditemLiveData.value = TRACK_LIST_ITEM
    }

    fun onSearchBottomBtnClicked() {

    }

    fun onTrackListBtnClicked() {

    }

    fun onTrendsBtnClicked() {

    }
}
