package com.github.kornilovmikhail.spoticloud.ui.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class LocalCiceroneHolder {

    companion object {
        private var containers = HashMap<String, Cicerone<Router>>()

        fun getCicerone(containerTag: String): Cicerone<Router> {
            if (!containers.containsKey(containerTag)) {
                containers[containerTag] = Cicerone.create()
            }
            return containers[containerTag] ?: Cicerone.create()
        }
    }
}
