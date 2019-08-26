package com.github.jaskelai.spoticloud.data.mappers

import com.github.jaskelai.spoticloud.data.network.model.AuthorSoundCloudRemote
import com.github.jaskelai.spoticloud.data.network.model.TrackSoundCloudResponse
import com.github.jaskelai.spoticloud.domain.model.Author
import com.github.jaskelai.spoticloud.domain.model.StreamServiceEnum
import com.github.jaskelai.spoticloud.domain.model.Track

fun mapSoundCloudTrackResponseToTrack(soundcloudTrack: TrackSoundCloudResponse): Track {
    return with(soundcloudTrack) {
        Track(
            0,
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
