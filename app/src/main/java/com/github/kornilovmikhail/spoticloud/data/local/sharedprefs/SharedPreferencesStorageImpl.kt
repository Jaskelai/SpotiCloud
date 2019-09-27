package com.github.kornilovmikhail.spoticloud.data.local.sharedprefs

import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.github.kornilovmikhail.spoticloud.domain.interfaces.SharedPreferencesStorage
import io.reactivex.Observable
import javax.inject.Inject

class SharedPreferencesStorageImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val rxSharedPreferences: RxSharedPreferences
): SharedPreferencesStorage {

    override fun writeMessage(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun readMessage(key: String): String? = sharedPreferences.getString(key, null)

    override fun observeMessage(key: String): Observable<String> {
        return rxSharedPreferences.getString(key, "").asObservable()
    }
}
