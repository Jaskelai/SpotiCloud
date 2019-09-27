package com.github.kornilovmikhail.spoticloud.ui.main.player

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class PlayerScreen: SupportAppScreen() {

    override fun getFragment(): Fragment = PlayerFragment.getInstance()
}
