package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.tracklist

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class TrackListScreen: SupportAppScreen() {

    override fun getFragment(): Fragment = TrackListFragment.getInstance()
}
