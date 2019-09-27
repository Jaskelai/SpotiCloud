package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.trendssoundcloud

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.domain.interactors.TrendsSoundCloudUseCase
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.github.kornilovmikhail.spoticloud.ui.utils.SingleEventLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TrendsSoundCloudViewModel @Inject constructor(
    private val trendsSoundCloudUseCase: TrendsSoundCloudUseCase
    ): ViewModel(), LifecycleObserver {

    val progressBarVisibilityLiveData = MutableLiveData<Boolean>()
    val soundcloudTracksLiveData = MutableLiveData<List<Track>>()
    val errorSoundCloudLiveData =
        SingleEventLiveData<String>()

    private val disposables = CompositeDisposable()

    init {
        fetchSoundCloudTracks()
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) disposables.dispose()
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
                    it.message?.let { message ->
                        errorSoundCloudLiveData.sendEvent(message)
                    }
                })
        )
    }
}
