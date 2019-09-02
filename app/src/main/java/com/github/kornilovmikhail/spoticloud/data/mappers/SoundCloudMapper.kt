package com.github.kornilovmikhail.spoticloud.data.mappers

import com.github.kornilovmikhail.spoticloud.data.network.model.soundcloud.AuthorSoundCloudRemote
import com.github.kornilovmikhail.spoticloud.data.network.model.soundcloud.TrackSoundCloudResponse
import com.github.kornilovmikhail.spoticloud.domain.model.Author
import com.github.kornilovmikhail.spoticloud.domain.model.StreamServiceEnum
import com.github.kornilovmikhail.spoticloud.domain.model.Track

fun mapSoundCloudTrackRemoteToTrack(soundcloudTrack: TrackSoundCloudResponse): Track {
    return with(soundcloudTrack) {
        Track(
            id.toString(),
            title,
            duration,
            StreamServiceEnum.SOUNDCLOUD,
            originalContentSize,
            null,
            artworkUrl,
            streamUrl,
            mapSoundCloudAuthorRemoteToAuthor(author)
        )
    }
}

fun mapSoundCloudAuthorRemoteToAuthor(authorSoundCloudRemote: AuthorSoundCloudRemote): Author {
    return with(authorSoundCloudRemote) {
        Author(username, avatarUrl)
    }
}
