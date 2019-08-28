package com.github.kornilovmikhail.spoticloud.data.network.model.spotify

import com.google.gson.annotations.SerializedName

data class TrackSpotifyRemote(
    val id: String,

    val album: AlbumSpotifyRemote?,

    val artists: List<AuthorSpotifyRemote>,

    @SerializedName("duration_ms")
    val duration: Int,

    @SerializedName("name")
    val title: String,

    @SerializedName("uri")
    val streamUrl: String
)
