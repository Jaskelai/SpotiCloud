package com.github.kornilovmikhail.spoticloud.ui.main.soundcloudauth

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SoundcloudAuthScreen: SupportAppScreen() {

    override fun getFragment(): Fragment = SoundcloudAuthFragment.getInstance()
}
