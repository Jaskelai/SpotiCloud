package com.github.kornilovmikhail.spoticloud.ui.main.settings

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SettingsScreen: SupportAppScreen() {

    override fun getFragment(): Fragment = SettingsFragment.getInstance()
}
