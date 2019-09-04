package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.github.kornilovmikhail.spoticloud.domain.interactors.CurrentTrackUseCase
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.navigation.LocalBottomNavRouter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BottomNavContainerViewModel @Inject constructor(
    private val localBottomNavRouter: LocalBottomNavRouter,
    private val currentTrackUseCase: CurrentTrackUseCase
) : ViewModel(), LifecycleObserver {

    companion object {
        const val FAV_TRACKS_SCREEN = 0
        const val CURRENT_SCREEN = 1
    }

    val selectedItemLiveData = MutableLiveData<Int>()
    val currentTrackLiveData = MutableLiveData<Track>()

    private val disposables = CompositeDisposable()

    init {
        selectedItemLiveData.value = FAV_TRACKS_SCREEN
        observeCurrentTrack()
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

    fun play(track: Track?) {
        track?.let {
            currentTrackUseCase.setCurrentTrack(it)
            currentTrackLiveData.value = it
        }
    }

    private fun observeCurrentTrack() {
        disposables.add(
            currentTrackUseCase.observeCurrentTrack()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    currentTrackLiveData.value = it
                }, {
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) disposables.dispose()
    }
}
