package com.github.kornilovmikhail.spoticloud.data.mappers

import com.github.kornilovmikhail.spoticloud.data.local.db.model.TrackDB
import com.github.kornilovmikhail.spoticloud.domain.model.Track

fun mapTrackDBToTrack(trackDB: TrackDB): Track {
    return with(trackDB) {
        Track(
            id,
            title,
            duration,
            streamService,
            originalContentSize,
            artworkLowSizeUrl,
            artworkUrl,
            streamUrl,
            author
        )
    }
}

fun mapTrackToTrackDB(track: Track): TrackDB {
    return with(track) {
        TrackDB(
            id,
            title,
            duration,
            streamService,
            originalContentSize,
            artworkLowSizeUrl,
            artworkUrl,
            streamUrl,
            author
        )
    }
}
