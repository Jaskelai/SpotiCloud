package com.github.kornilovmikhail.spoticloud.domain.interfaces

import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.Single

interface TracksRepository {

    fun getFavTracks(): Single<List<Track>>
}