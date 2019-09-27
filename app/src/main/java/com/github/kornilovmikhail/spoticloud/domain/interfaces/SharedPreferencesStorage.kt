package com.github.kornilovmikhail.spoticloud.domain.interfaces

import io.reactivex.Observable

interface SharedPreferencesStorage {

    fun writeMessage(key: String, value: String)

    fun readMessage(key: String): String?

    fun observeMessage(key: String): Observable<String>
}
