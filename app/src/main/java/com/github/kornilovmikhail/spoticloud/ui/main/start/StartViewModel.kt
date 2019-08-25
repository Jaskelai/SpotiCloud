package com.github.kornilovmikhail.spoticloud.ui.main.start

import android.annotation.SuppressLint
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MediatorLiveData
import com.github.kornilovmikhail.spoticloud.domain.interactors.CommonAuthUseCase
import com.github.kornilovmikhail.spoticloud.domain.interactors.SoundCloudAuthUseCase
import com.github.kornilovmikhail.spoticloud.domain.interactors.SpotifyAuthUseCase
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.Router
import com.spotify.sdk.android.authentication.AuthenticationRequest
import javax.inject.Inject

class StartViewModel @Inject constructor(
    private val router: Router,
    private val soundCloudAuthUseCase: SoundCloudAuthUseCase,
    private val spotifyAuthUseCase: SpotifyAuthUseCase,
    private val commonAuthUseCase: CommonAuthUseCase,
    private val request: AuthenticationRequest
) : ViewModel(), LifecycleObserver {

    companion object {
        private const val REQUEST_CODE_SPOTIFY = 1337
        private const val EXTRA_AUTH_RESPONSE = "EXTRA_AUTH_RESPONSE"
    }

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
        router.navigateToSoundcloudAuthScreen()
    }

    fun onBtnAuthSpotifyClicked(fragment: Fragment?) {
        router.navigateToSpotifyAuthScreen(fragment, request, REQUEST_CODE_SPOTIFY)
    }

    fun onBtnSnackbarClicked() {
        commonAuthUseCase.saveAuthStatus(true)
    }

    fun handleResult(requestCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_SPOTIFY) {
            val dataBundle = data?.getBundleExtra(EXTRA_AUTH_RESPONSE)
            var value: Any? = null
            dataBundle?.let {
                for (key in dataBundle.keySet()) {
                    value = dataBundle.get(key)
                }
            }
            onSpotifyResult(value)
        }
    }

    private fun onSpotifyResult(any: Any?) {
        spotifyAuthUseCase.auth(any)
            .subscribe()
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
