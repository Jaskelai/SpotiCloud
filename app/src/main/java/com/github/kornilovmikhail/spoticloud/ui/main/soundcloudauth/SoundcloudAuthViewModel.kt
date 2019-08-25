package com.github.kornilovmikhail.spoticloud.ui.main.soundcloudauth

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.domain.interactors.SoundCloudAuthUseCase
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import javax.inject.Inject

const val STATUS_BAD_CONNECTION = 0
const val STATUS_BAD_CREDENTIALS = 1

class SoundcloudAuthViewModel @Inject constructor(
    private val router: Router,
    private val soundCloudAuthUseCase: SoundCloudAuthUseCase
) : ViewModel(), LifecycleObserver {

    private val disposables = CompositeDisposable()

    val progressBarVisibilityLiveData = MutableLiveData<Boolean>()
    val errorCodeLiveData = MutableLiveData<Int>()

    init {
        progressBarVisibilityLiveData.value = false

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
                    if (it is HttpException) {
                        errorCodeLiveData.value = STATUS_BAD_CREDENTIALS
                    } else {
                        errorCodeLiveData.value = STATUS_BAD_CONNECTION
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) disposables.dispose()
    }
}