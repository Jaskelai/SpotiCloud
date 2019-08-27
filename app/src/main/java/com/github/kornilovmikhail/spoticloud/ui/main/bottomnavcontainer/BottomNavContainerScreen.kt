package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class BottomNavContainerScreen : SupportAppScreen() {

    override fun getFragment(): Fragment = BottomNavContainerFragment.getInstance()
}
