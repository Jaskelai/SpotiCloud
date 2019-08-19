package com.github.kornilovmikhail.spoticloud.data.local.sharedprefs

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SharedPreferencesStorage @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun writeMessage(key: String, value: String) {
        Completable.fromAction {
            sharedPreferences.edit().putString(key, value).apply()
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun readMessage(key: String): Single<String> = Single.fromCallable {
        sharedPreferences.getString(key, "")
    }
}
