package com.github.kornilovmikhail.spoticloud.ui.main.feature.soundcloudauth

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.domain.interactors.SoundCloudAuthUseCase
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SoundcloudAuthViewModel @Inject constructor(
    private val router: Router,
    private val soundCloudAuthUseCase: SoundCloudAuthUseCase
) : ViewModel(), LifecycleObserver {

    private val disposables = CompositeDisposable()

    val progressBarVisibilityLiveData = MutableLiveData<Boolean>()
    val errorVisibilityLiveData = MutableLiveData<Boolean>()

    init {
        progressBarVisibilityLiveData.value = false
        errorVisibilityLiveData.value = false
    }

    fun onBackButtonClicked() {
        router.navigateToStartScreen()
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
                    router.navigateToStartScreen()
                }, {
                    println(it.message)
                })
        )
    }
}
