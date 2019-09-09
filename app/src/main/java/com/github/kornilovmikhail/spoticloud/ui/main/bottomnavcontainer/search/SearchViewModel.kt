package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.search

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Lifecycle
import com.github.kornilovmikhail.spoticloud.domain.interactors.TrackSearchUseCase
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val trackSearchUseCase: TrackSearchUseCase
) : ViewModel(), LifecycleObserver {

    val progressBarVisibilityLiveData = MutableLiveData<Boolean>()
    val searchResultLiveData = MutableLiveData<List<Track>>()

    private val disposables = CompositeDisposable()

    init {
        progressBarVisibilityLiveData.value = false
    }


    fun searchForTracks(observable: Observable<TextViewTextChangeEvent>) {
        disposables.add(
            observable
                .skip(1)
                .cache()
                .map {
                    it.text }
                .debounce(700, TimeUnit.MILLISECONDS)
                .filter { it.isNotEmpty() }
                .doOnNext { progressBarVisibilityLiveData.postValue(true) }
                .flatMap {
                    trackSearchUseCase.searchForTracks(it.toString()).toObservable()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach { progressBarVisibilityLiveData.value = false }
                .subscribe({
                    searchResultLiveData.value = it
                }, {
                    it.printStackTrace()
                })
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun unsubscribe() {
        disposables.clear()
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) disposables.dispose()
    }
}
