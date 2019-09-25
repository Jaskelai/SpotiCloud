package com.github.kornilovmikhail.spoticloud.ui.navigation.router

import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.BottomNavContainerScreen
import com.github.kornilovmikhail.spoticloud.ui.main.player.PlayerScreen
import com.github.kornilovmikhail.spoticloud.ui.main.soundcloudauth.SoundcloudAuthScreen
import com.github.kornilovmikhail.spoticloud.ui.main.spotifyauth.SpotifyAuthScreen
import com.github.kornilovmikhail.spoticloud.ui.main.start.StartScreen
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

class GlobalRouterCiceroneImpl @Inject constructor() : GlobalRouter, ru.terrakok.cicerone.Router() {

    override fun navigateToStartScreen() {
        executeCommands(BackTo(null), Replace(StartScreen()))
    }

    override fun navigateToSoundcloudAuthScreen() {
        executeCommands(Forward(SoundcloudAuthScreen()))
    }

    override fun navigateToSpotifyAuthScreen() {
        executeCommands(Forward(SpotifyAuthScreen()))
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
