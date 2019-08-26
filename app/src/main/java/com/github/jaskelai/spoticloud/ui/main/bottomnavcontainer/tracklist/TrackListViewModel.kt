package com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer.tracklist

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Lifecycle
import com.github.jaskelai.spoticloud.domain.interactors.FavTracksUseCase
import com.github.jaskelai.spoticloud.domain.model.Track
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TrackListViewModel @Inject constructor(
    private val favTracksUseCase: FavTracksUseCase
) : ViewModel(), LifecycleObserver {

    val trackListLiveData = MutableLiveData<List<Track>>()
    val progressBarVisibilityLiveData = MutableLiveData<Boolean>()

    private val disposables = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchTracks() {
        disposables.add(
            favTracksUseCase.getFavTracks()
                .doOnSubscribe {
                    progressBarVisibilityLiveData.postValue(true)
                }
                .doAfterTerminate {
                    progressBarVisibilityLiveData.postValue(false)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    trackListLiveData.value = it
                }, {
                    it.printStackTrace()
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) disposables.dispose()
    }
}
