package com.github.kornilovmikhail.spoticloud.data.mappers

import com.github.kornilovmikhail.spoticloud.data.local.db.model.AuthorDB
import com.github.kornilovmikhail.spoticloud.data.local.db.model.TrackDB
import com.github.kornilovmikhail.spoticloud.domain.model.Author
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
            mapAuthorDBtoAuthor(author)
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
            mapAuthorToAuthorDB(author)
        )
    }
}

fun mapAuthorToAuthorDB(author: Author): AuthorDB {
    return with(author) {
        AuthorDB(name, avatarUrl)
    }
}

fun mapAuthorDBtoAuthor(authorDB: AuthorDB): Author {
    return with(authorDB) {
        Author(name, avatarUrl)
    }
}
