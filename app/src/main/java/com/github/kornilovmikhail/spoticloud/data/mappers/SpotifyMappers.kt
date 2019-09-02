package com.github.kornilovmikhail.spoticloud.data.mappers

import com.github.kornilovmikhail.spoticloud.data.network.model.spotify.TrackSpotifyRemote
import com.github.kornilovmikhail.spoticloud.domain.model.Author
import com.github.kornilovmikhail.spoticloud.domain.model.StreamServiceEnum
import com.github.kornilovmikhail.spoticloud.domain.model.Track

private const val IMAGE_LOW_SIZE = 70
private const val IMAGE_BIG_SIZE = 500

fun mapSpotifyTrackRemoteToTrack(spotifyTrack: TrackSpotifyRemote): Track =
    with(spotifyTrack) {
        Track(
            id,
            title,
            duration,
            StreamServiceEnum.SPOTIFY,
            null,
            album?.image?.filter { it.size < IMAGE_LOW_SIZE }?.get(0)?.url,
            album?.image?.filter { it.size > IMAGE_BIG_SIZE }?.get(0)?.url,
            streamUrl,
            Author(artists[0].name, artists[0].link)
        )
    }