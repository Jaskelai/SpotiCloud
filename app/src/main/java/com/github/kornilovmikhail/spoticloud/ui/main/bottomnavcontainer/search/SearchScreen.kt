package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.search

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SearchScreen: SupportAppScreen() {

    override fun getFragment(): Fragment = SearchFragment.getInstance()
}
