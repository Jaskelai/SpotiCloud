package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer

import androidx.lifecycle.*
import com.github.kornilovmikhail.spoticloud.domain.interactors.CurrentTrackUseCase
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.navigation.LocalBottomNavRouter
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.GlobalRouter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BottomNavContainerViewModel @Inject constructor(
    private val localBottomNavRouter: LocalBottomNavRouter,
    private val globalRouter: GlobalRouter,
    private val currentTrackUseCase: CurrentTrackUseCase
) : ViewModel(), LifecycleObserver {

    companion object {
        const val FAV_TRACKS_SCREEN = 0
        const val CURRENT_SCREEN = 1
    }

    val selectedItemLiveData = MutableLiveData<Int>()
    val isFooterEnabledLiveData = MutableLiveData<Boolean>()
    val currentTrackLiveData = MutableLiveData<Track>()

    private val disposables = CompositeDisposable()

    init {
        isFooterEnabledLiveData.value = false
        selectedItemLiveData.value = FAV_TRACKS_SCREEN
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun observeCurrentTrack() {
        disposables.add(
            currentTrackUseCase.observeCurrentTrack()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    updateFooter(it)
                }, {
                })
        )
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

    fun onFooterClicked() {
        globalRouter.navigateToPlayerScreen()
    }

    fun play(track: Track?) {
        track?.let {
            currentTrackUseCase.setCurrentTrack(it)
            updateFooter(track)
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) disposables.dispose()
    }

    private fun updateFooter(track: Track) {
        isFooterEnabledLiveData.value = true
        currentTrackLiveData.value = track
    }
}
