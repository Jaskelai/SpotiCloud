package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class TrendsContainerScreen: SupportAppScreen() {

    override fun getFragment(): Fragment = TrendsContainerFragment.getInstance()
}
