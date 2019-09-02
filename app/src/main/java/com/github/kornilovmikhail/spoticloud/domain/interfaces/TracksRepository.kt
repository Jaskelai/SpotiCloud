package com.github.kornilovmikhail.spoticloud.domain.interfaces

import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single

interface TracksRepository {

    fun getFavTracks(): Single<List<Track>>

    fun getTrendTracks(): Single<List<Track>>

    fun searchForTracks(text: String): Single<List<Track>>
}
