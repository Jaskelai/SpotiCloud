package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.domain.interactors.TrendsSoundCloudUseCase
import com.github.kornilovmikhail.spoticloud.domain.interactors.TrendsSpotifyUseCase
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TrendsContainerViewModel @Inject constructor(
    private val trendsSoundCloudUseCase: TrendsSoundCloudUseCase,
    private val trendsSpotifyUseCase: TrendsSpotifyUseCase
) : ViewModel(), LifecycleObserver {

    val progressBarVisibilityLiveData = MutableLiveData<Boolean>()
    val soundcloudTracksLiveData = MutableLiveData<List<Track>>()
    val spotifyTracksLiveData = MutableLiveData<List<Track>>()

    private val disposables = CompositeDisposable()

    init {
        fetchSoundCloudTracks()
        fetchSpotifyTracks()
    }

    private fun fetchSoundCloudTracks() {
        disposables.add(
            trendsSoundCloudUseCase.getTrendsTracks()
                .doOnSubscribe {
                    progressBarVisibilityLiveData.postValue(true)
                }
                .doAfterTerminate {
                    progressBarVisibilityLiveData.postValue(false)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    soundcloudTracksLiveData.value = it
                }, {
                    it.printStackTrace()
                })
        )
    }

    private fun fetchSpotifyTracks() {
        disposables.add(
            trendsSpotifyUseCase.getTrendsTracks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    spotifyTracksLiveData.value = it
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
