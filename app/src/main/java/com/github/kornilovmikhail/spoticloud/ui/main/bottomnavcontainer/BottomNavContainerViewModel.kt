package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer

import androidx.lifecycle.*
import com.github.kornilovmikhail.spoticloud.domain.interactors.CurrentTrackUseCase
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.GlobalRouter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BottomNavContainerViewModel @Inject constructor(
    private val globalRouter: GlobalRouter,
    private val currentTrackUseCase: CurrentTrackUseCase
) : ViewModel(), LifecycleObserver {

    val isFooterEnabledLiveData = MutableLiveData<Boolean>()
    val currentTrackLiveData = MutableLiveData<Track>()

    private val disposables = CompositeDisposable()

    init {
        isFooterEnabledLiveData.value = false
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
