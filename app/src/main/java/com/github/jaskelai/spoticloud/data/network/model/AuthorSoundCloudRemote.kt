package com.github.jaskelai.spoticloud.data.network.model

import com.google.gson.annotations.SerializedName

data class AuthorSoundCloudRemote(
    val username: String,

    @SerializedName("avatar_url")
    val avatarUrl: String
)
