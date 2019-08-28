package com.github.kornilovmikhail.spoticloud.data.network.model.spotify

import com.google.gson.annotations.SerializedName

data class TrackImageSpotifyRemote(
    @SerializedName("height")
    val size: Int,

    val url: String
)
