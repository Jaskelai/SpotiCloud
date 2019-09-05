package com.github.kornilovmikhail.spoticloud.ui.navigation.router

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.BottomNavContainerScreen
import com.github.kornilovmikhail.spoticloud.ui.main.player.PlayerScreen
import com.github.kornilovmikhail.spoticloud.ui.main.soundcloudauth.SoundcloudAuthScreen
import com.github.kornilovmikhail.spoticloud.ui.main.start.StartScreen
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.LoginActivity
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

class GlobalRouterCiceroneImpl @Inject constructor() : GlobalRouter, ru.terrakok.cicerone.Router() {

    companion object {
        private const val EXTRA_AUTH_REQUEST = "EXTRA_AUTH_REQUEST"
    }

    override fun navigateToStartScreen() {
        executeCommands(BackTo(null), Replace(StartScreen()))
    }

    override fun navigateToSoundcloudAuthScreen() {
        executeCommands(Forward(SoundcloudAuthScreen()))
    }

    override fun navigateToSpotifyAuthScreen(
        fragment: Fragment?,
        request: AuthenticationRequest?,
        requestCode: Int
    ) {
        if (fragment == null || request == null) {
            throw IllegalArgumentException("Context activity or request can't be null")
        }
        val bundle = Bundle()
        bundle.putParcelable(LoginActivity.REQUEST_KEY, request)
        val intent = Intent(fragment.context, LoginActivity::class.java)
        intent.putExtra(EXTRA_AUTH_REQUEST, bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        fragment.startActivityForResult(intent, requestCode)
    }

    override fun navigateToBottomNavScreen() {
        executeCommands(
            BackTo(null),
            Replace(BottomNavContainerScreen())
        )
    }

    override fun navigateToPlayerScreen() {
        executeCommands(Forward(PlayerScreen()))
    }

    override fun returnToBottomNavScreen() {
        executeCommands(Back())
    }
}
