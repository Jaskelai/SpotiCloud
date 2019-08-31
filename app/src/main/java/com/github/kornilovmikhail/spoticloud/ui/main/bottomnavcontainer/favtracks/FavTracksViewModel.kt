package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.favtracks

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.domain.interactors.FavTracksUseCase
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FavTracksViewModel @Inject constructor(
    private val favTracksUseCase: FavTracksUseCase
) : ViewModel(), LifecycleObserver {

    val trackListLiveData = MutableLiveData<List<Track>>()
    val progressBarVisibilityLiveData = MutableLiveData<Boolean>()

    private val disposables = CompositeDisposable()

    init {
        fetchTracks()
    }

    private fun fetchTracks() {
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
