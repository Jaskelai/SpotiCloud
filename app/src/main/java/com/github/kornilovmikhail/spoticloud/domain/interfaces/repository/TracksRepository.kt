package com.github.kornilovmikhail.spoticloud.domain.interfaces.repository

import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Completable
import io.reactivex.Single

interface TracksRepository {

    fun getFavTracks(): Single<List<Track>>

    fun getTrendTracks(): Single<List<Track>>

    fun searchForTracks(text: String): Single<List<Track>>

    fun addTrackToFav(track: Track): Completable

    fun deleteTrackFromFav(track: Track): Completable
}
