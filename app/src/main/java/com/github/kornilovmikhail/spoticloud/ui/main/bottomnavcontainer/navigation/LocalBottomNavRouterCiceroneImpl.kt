package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.navigation

import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.tracklist.TrackListScreen
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsContainerScreen
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

class LocalBottomNavRouterCiceroneImpl @Inject constructor() : LocalBottomNavRouter, Router() {

    override fun navigateToTrackListScreen() {
        executeCommands(Forward(TrackListScreen()))
    }

    override fun navigateToTrendsScreen() {
        executeCommands(Forward(TrendsContainerScreen()))
    }
}
