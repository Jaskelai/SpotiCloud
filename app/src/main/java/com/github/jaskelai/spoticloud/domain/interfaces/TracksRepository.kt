package com.github.jaskelai.spoticloud.domain.interfaces

import com.github.jaskelai.spoticloud.domain.model.Track
import io.reactivex.Single

interface TracksRepository {

    fun getFavTracks(): Single<List<Track>>
}
