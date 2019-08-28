package com.github.kornilovmikhail.spoticloud.data.network.model.soundcloud

import com.google.gson.annotations.SerializedName

data class TrackSoundCloudResponse(
    val id: Int,

    val duration: Int,

    @SerializedName("original_content_size")
    val originalContentSize: Int,

    val title: String,

    @SerializedName("artwork_url")
    val artworkUrl: String?,

    @SerializedName("stream_url")
    val streamUrl: String,

    @SerializedName("user")
    val author: AuthorSoundCloudRemote
)
