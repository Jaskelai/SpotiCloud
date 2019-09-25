package com.github.kornilovmikhail.spoticloud.ui.navigation.router

interface GlobalRouter {

    fun navigateToStartScreen()

    fun navigateToSoundcloudAuthScreen()

    fun navigateToSpotifyAuthScreen()

    fun navigateToBottomNavScreen()

    fun navigateToPlayerScreen()

    fun returnToBottomNavScreen()
}
