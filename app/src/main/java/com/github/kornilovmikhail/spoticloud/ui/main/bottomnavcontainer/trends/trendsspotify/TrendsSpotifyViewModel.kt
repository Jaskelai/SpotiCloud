package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.trendsspotify

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.domain.interactors.TrendsSpotifyUseCase
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.github.kornilovmikhail.spoticloud.utils.SingleEventLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TrendsSpotifyViewModel @Inject constructor(
    private val trendsSpotifyUseCase: TrendsSpotifyUseCase
): ViewModel(), LifecycleObserver {

    val progressBarVisibilityLiveData = MutableLiveData<Boolean>()
    val spotifyTracksLiveData = MutableLiveData<List<Track>>()
    val errorSpotifyLiveData = SingleEventLiveData<String>()

    private val disposables = CompositeDisposable()

    init {
        fetchSpotifyTracks()
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) disposables.dispose()
    }

    private fun fetchSpotifyTracks() {
        disposables.add(
            trendsSpotifyUseCase.getTrendsTracks()
                .doOnSubscribe {
                    progressBarVisibilityLiveData.postValue(true)
                }
                .doAfterTerminate {
                    progressBarVisibilityLiveData.postValue(false)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    spotifyTracksLiveData.value = it
                }, {
                    it.message?.let { message ->
                        errorSpotifyLiveData.sendEvent(message)
                    }
                })
        )
    }
}
