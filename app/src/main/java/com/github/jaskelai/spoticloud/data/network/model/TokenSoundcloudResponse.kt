package com.github.jaskelai.spoticloud.data.network.model

import com.google.gson.annotations.SerializedName

data class TokenSoundcloudResponse(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("expires_in")
    val expiresIn: Int,

    @SerializedName("refresh_token")
    val refreshToken: String
)
