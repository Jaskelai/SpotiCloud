package com.github.kornilovmikhail.spoticloud.ui.main.spotifyauth

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.domain.interactors.SpotifyAuthUseCase
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.GlobalRouter
import com.github.kornilovmikhail.spoticloud.ui.utils.SingleEventLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SpotifyAuthViewModel @Inject constructor(
    private val spotifyAuthUseCase: SpotifyAuthUseCase,
    private val globalRouter: GlobalRouter
) : ViewModel(), LifecycleObserver {

    fun getAuthUrl(): String = spotifyAuthUseCase.getAuthUrl()

    val progressBarVisibilityLiveData = MutableLiveData<Boolean>()
    val errorLiveData = SingleEventLiveData<String>()

    private val disposables = CompositeDisposable()

    init {
        progressBarVisibilityLiveData.value = true
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) disposables.dispose()
    }

    fun onPageLoading(code: String?) {
        code?.let {
            auth(it)
        }
    }

    fun onPageLoaded() {
        progressBarVisibilityLiveData.value = false
    }

    private fun auth(code: String) {
        disposables.add(
            spotifyAuthUseCase.auth(code)
                .doOnSubscribe {
                    progressBarVisibilityLiveData.postValue(true)
                }
                .doAfterTerminate {
                    progressBarVisibilityLiveData.postValue(false)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    globalRouter.navigateToStartScreen()
                }, {
                    errorLiveData.value = it.message
                })
        )
    }
}
