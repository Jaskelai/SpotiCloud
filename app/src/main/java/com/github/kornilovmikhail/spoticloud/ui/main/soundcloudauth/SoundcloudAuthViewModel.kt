package com.github.kornilovmikhail.spoticloud.ui.main.soundcloudauth

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.domain.interactors.SoundCloudAuthUseCase
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.GlobalRouter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SoundcloudAuthViewModel @Inject constructor(
    private val globalRouter: GlobalRouter,
    private val soundCloudAuthUseCase: SoundCloudAuthUseCase
) : ViewModel(), LifecycleObserver {

    val progressBarVisibilityLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()

    private val disposables = CompositeDisposable()

    init {
        progressBarVisibilityLiveData.value = false

    }

    fun onBackButtonClicked() {
        globalRouter.navigateToStartScreen()
    }

    fun onSigninButtonClicked(email: String, password: String) {
        disposables.add(
            soundCloudAuthUseCase.auth(email, password)
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

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) disposables.dispose()
    }
}
