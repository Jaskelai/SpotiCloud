package com.github.kornilovmikhail.spoticloud.ui.main.feature.start

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.Router
import javax.inject.Inject

class StartViewModel @Inject constructor(private val router: Router): ViewModel(), LifecycleObserver {

    fun onBtnAuthSoundcloudClick() {

    }
}
