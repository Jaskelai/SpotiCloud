package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.favtracks

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FavTracksScreen: SupportAppScreen() {

    override fun getFragment(): Fragment = FavTracksFragment.getInstance()
}
