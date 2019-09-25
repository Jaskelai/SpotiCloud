package com.github.kornilovmikhail.spoticloud.ui.main.start

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MediatorLiveData
import com.github.kornilovmikhail.spoticloud.domain.interactors.CommonAuthUseCase
import com.github.kornilovmikhail.spoticloud.domain.interactors.SoundCloudAuthUseCase
import com.github.kornilovmikhail.spoticloud.domain.interactors.SpotifyAuthUseCase
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.GlobalRouter
import javax.inject.Inject

class StartViewModel @Inject constructor(
    private val globalRouter: GlobalRouter,
    private val soundCloudAuthUseCase: SoundCloudAuthUseCase,
    private val spotifyAuthUseCase: SpotifyAuthUseCase,
    private val commonAuthUseCase: CommonAuthUseCase
) : ViewModel(), LifecycleObserver {

    val authedLiveData = MediatorLiveData<Boolean>()
    val soundcloudBtnActiveLiveData = MutableLiveData<Boolean>()
    val spotifyBtnActiveLiveData = MutableLiveData<Boolean>()

    init {
        authedLiveData.value = false
        soundcloudBtnActiveLiveData.value = true
        spotifyBtnActiveLiveData.value = true

        authedLiveData.addSource(soundcloudBtnActiveLiveData) {
            if (!it) authedLiveData.value = true
        }
        authedLiveData.addSource(spotifyBtnActiveLiveData) {
            if (!it) authedLiveData.value = true
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        checkSoundcloudAuth()
        checkSpotifyAuth()
    }

    fun onBtnAuthSoundcloudClicked() {
        globalRouter.navigateToSoundcloudAuthScreen()
    }

    fun onBtnAuthSpotifyClicked() {
        globalRouter.navigateToSpotifyAuthScreen()
    }

    fun onBtnSnackbarClicked() {
        commonAuthUseCase.saveAuthStatus(true)
        globalRouter.navigateToBottomNavScreen()
    }

    @SuppressLint("CheckResult")
    private fun checkSoundcloudAuth() {
        val isSoundcloudAuthed = soundCloudAuthUseCase.checkAuth()
        if (isSoundcloudAuthed) {
            soundcloudBtnActiveLiveData.value = false
        }
    }

    @SuppressLint("CheckResult")
    private fun checkSpotifyAuth() {
        val isSpotifyAuthed = spotifyAuthUseCase.checkAuth()
        if (isSpotifyAuthed) {
            spotifyBtnActiveLiveData.value = false
        }
    }
}
