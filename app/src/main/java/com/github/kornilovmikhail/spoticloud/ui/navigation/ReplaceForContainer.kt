package com.github.kornilovmikhail.spoticloud.ui.navigation

import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.commands.Command

class ReplaceForContainer (private val screen: Screen): Command {

    fun getScreen(): Screen = screen
}
