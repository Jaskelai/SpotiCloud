package com.github.kornilovmikhail.spoticloud.ui.main.spotifyauth

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SpotifyAuthScreen: SupportAppScreen() {

    override fun getFragment(): Fragment = SpotifyAuthFragment.getInstance()
}
