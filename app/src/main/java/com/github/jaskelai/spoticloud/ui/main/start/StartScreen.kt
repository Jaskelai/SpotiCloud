package com.github.jaskelai.spoticloud.ui.main.start

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class StartScreen: SupportAppScreen() {

    override fun getFragment(): Fragment = StartFragment.getInstance()
}
