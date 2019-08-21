package com.github.kornilovmikhail.spoticloud.data.local.sharedprefs

import android.content.SharedPreferences
import io.reactivex.Maybe
import javax.inject.Inject

class SharedPreferencesStorage @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun writeMessage(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun readMessage(key: String): Maybe<String> = Maybe.fromCallable {
        sharedPreferences.getString(key, null)
    }
}
