package com.github.kornilovmikhail.spoticloud.ui.main.player

import androidx.lifecycle.*
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

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) disposables.dispose()
    }

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
}
