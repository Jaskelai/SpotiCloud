package com.github.kornilovmikhail.spoticloud.data.network.model.spotify

import com.google.gson.annotations.SerializedName

data class AlbumSpotifyRemote(
    @SerializedName("images")
    val image: List<TrackImageSpotifyRemote>,

    val name: String
)
