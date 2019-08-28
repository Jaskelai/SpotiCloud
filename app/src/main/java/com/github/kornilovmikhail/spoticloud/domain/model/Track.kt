package com.github.kornilovmikhail.spoticloud.domain.model

data class Track(

    val id: String,

    val title: String,

    val duration: Int?,

    val streamService: StreamServiceEnum,

    val originalContentSize: Int?,

    val artworkLowSizeUrl: String?,

    val artworkUrl: String?,

    val streamUrl: String,

    val author: Author
)
