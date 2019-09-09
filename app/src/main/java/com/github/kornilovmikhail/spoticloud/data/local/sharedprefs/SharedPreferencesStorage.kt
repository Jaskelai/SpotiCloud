package com.github.kornilovmikhail.spoticloud.data.local.sharedprefs

import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.Observable
import javax.inject.Inject

class SharedPreferencesStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val rxSharedPreferences: RxSharedPreferences
) {

    fun writeMessage(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun readMessage(key: String): String? = sharedPreferences.getString(key, null)

    fun observeMessage(key: String): Observable<String> {
        return rxSharedPreferences.getString(key, "").asObservable()
    }
}
