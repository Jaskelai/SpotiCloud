package com.github.kornilovmikhail.spoticloud.data.network.model.spotify

import com.google.gson.annotations.SerializedName

data class AuthorSpotifyRemote(
    @SerializedName("href")
    val link: String,

    val name: String
)
