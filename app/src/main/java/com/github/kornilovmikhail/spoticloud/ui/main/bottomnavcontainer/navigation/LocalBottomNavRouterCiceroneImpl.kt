package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.navigation

import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.tracklist.TrackListScreen
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

class LocalBottomNavRouterCiceroneImpl @Inject constructor() : LocalBottomNavRouter, Router() {

    override fun navigateToTrackListScreen() {
        executeCommands(Forward(TrackListScreen()))
    }
}
