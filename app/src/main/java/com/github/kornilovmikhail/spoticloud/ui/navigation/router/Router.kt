package com.github.kornilovmikhail.spoticloud.ui.navigation.router

import androidx.fragment.app.Fragment
import com.spotify.sdk.android.authentication.AuthenticationRequest

interface Router {

    fun navigateToStartScreen()

    fun navigateToSoundcloudAuthScreen()

    fun navigateToSpotifyAuthScreen(
        fragment: Fragment?,
        request: AuthenticationRequest?,
        requestCode: Int
    )
}
