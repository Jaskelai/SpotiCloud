package com.github.kornilovmikhail.spoticloud.data.repository

import com.github.kornilovmikhail.spoticloud.data.local.sharedprefs.SharedPreferencesStorage
import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.CurrentTrackRepository
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrentTrackRepositoryImpl @Inject constructor(
    private val sharedPreferencesStorage: SharedPreferencesStorage,
    private val gson: Gson
) : CurrentTrackRepository {

    companion object {
        private const val CURRENT_TRACK = "current_track"
    }

    override fun setCurrentTrack(track: Track) {
        return sharedPreferencesStorage.writeMessage(CURRENT_TRACK, gson.toJson(track))
    }

    override fun observeCurrentTrack(): Observable<Track> {
        return sharedPreferencesStorage.observeMessage(CURRENT_TRACK)
            .map {
                gson.fromJson(it, Track::class.java)
            }
            .subscribeOn(Schedulers.io())
    }
}
