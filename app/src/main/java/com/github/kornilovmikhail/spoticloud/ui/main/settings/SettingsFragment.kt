package com.github.kornilovmikhail.spoticloud.ui.main.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.github.kornilovmikhail.spoticloud.R

class SettingsFragment: PreferenceFragmentCompat() {

    companion object {

        fun getInstance() = SettingsFragment()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_screen, rootKey)
    }
}
