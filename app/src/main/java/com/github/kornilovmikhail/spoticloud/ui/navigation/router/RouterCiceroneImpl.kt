package com.github.kornilovmikhail.spoticloud.ui.navigation.router

import com.github.kornilovmikhail.spoticloud.ui.main.feature.soundcloudauth.SoundcloudAuthScreen
import com.github.kornilovmikhail.spoticloud.ui.main.feature.start.StartScreen
import ru.terrakok.cicerone.BaseRouter
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

class RouterCiceroneImpl @Inject constructor(): Router, BaseRouter() {

    override fun navigateToStartScreen() {
        executeCommands(BackTo(null), Replace(StartScreen()))
    }

    override fun navigateToSoundcloudAuthScreen() {
        executeCommands(Forward(SoundcloudAuthScreen()))
    }
}
