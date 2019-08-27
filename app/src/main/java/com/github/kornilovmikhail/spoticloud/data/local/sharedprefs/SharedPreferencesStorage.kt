package com.github.kornilovmikhail.spoticloud.data.local.sharedprefs

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesStorage @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun writeMessage(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun readMessage(key: String): String? = sharedPreferences.getString(key, null)
}
