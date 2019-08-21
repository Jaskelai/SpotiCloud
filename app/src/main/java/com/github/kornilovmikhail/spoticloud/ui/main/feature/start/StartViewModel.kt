package com.github.kornilovmikhail.spoticloud.ui.main.feature.start

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MediatorLiveData
import com.github.kornilovmikhail.spoticloud.domain.interactors.SoundCloudAuthUseCase
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.Router
import javax.inject.Inject

class StartViewModel @Inject constructor(
    private val router: Router,
    private val soundCloudAuthUseCase: SoundCloudAuthUseCase
) : ViewModel(), LifecycleObserver {

    val authedLiveData = MediatorLiveData<Boolean>()
    val soundcloudBtnActiveLiveData = MutableLiveData<Boolean>()

    init {
        authedLiveData.value = false
        soundcloudBtnActiveLiveData.value = true

        authedLiveData.addSource(soundcloudBtnActiveLiveData) {
            if (!it) authedLiveData.value = true
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        checkSoundcloudAuth()
    }

    fun onBtnAuthSoundcloudClicked() {
        router.navigateToSoundcloudAuthScreen()
    }

    fun onBtnSnackbarClicked() {

    }

    @SuppressLint("CheckResult")
    private fun checkSoundcloudAuth() {
        soundCloudAuthUseCase.loadSavedToken()
            .subscribe({
                soundcloudBtnActiveLiveData.value = false
            }, {
                it.printStackTrace()
            })
    }
}
