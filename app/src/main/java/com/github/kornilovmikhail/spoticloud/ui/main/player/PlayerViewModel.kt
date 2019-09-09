package com.github.kornilovmikhail.spoticloud.ui.main.player

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Lifecycle
import com.github.kornilovmikhail.spoticloud.domain.interactors.CurrentTrackUseCase
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.GlobalRouter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
    private val globalRouter: GlobalRouter,
    private val currentTrackUseCase: CurrentTrackUseCase
) : ViewModel(), LifecycleObserver {

    val trackLiveData = MutableLiveData<Track>()

    private val disposables = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun observeCurrentTrack() {
        disposables.add(
            currentTrackUseCase.observeCurrentTrack()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    trackLiveData.value = it
                }, {
                })
        )
    }

    fun onBackButtonClicked() {
        globalRouter.returnToBottomNavScreen()
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) disposables.dispose()
    }
}
