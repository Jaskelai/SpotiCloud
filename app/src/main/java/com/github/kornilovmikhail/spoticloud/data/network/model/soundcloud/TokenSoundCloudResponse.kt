package com.github.kornilovmikhail.spoticloud.data.network.model.soundcloud

import com.google.gson.annotations.SerializedName

data class TokenSoundCloudResponse(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("expires_in")
    val expiresIn: Int,

    @SerializedName("refresh_token")
    val refreshToken: String
)
