package com.github.kornilovmikhail.spoticloud.data.local.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Embedded
import com.github.kornilovmikhail.spoticloud.domain.model.Author
import com.github.kornilovmikhail.spoticloud.domain.model.StreamServiceEnum

@Entity(
    tableName = "track"
)
data class TrackDB(

    @PrimaryKey
    @ColumnInfo(name = "source_id")
    val id: String,

    val title: String,

    val duration: Int?,

    @ColumnInfo(name = "stream_service")
    val streamService: StreamServiceEnum,

    @ColumnInfo(name = "original_content_size")
    val originalContentSize: Int?,

    @ColumnInfo(name = "artwork_url_lowsize")
    val artworkLowSizeUrl: String?,

    @ColumnInfo(name = "artwork_url")
    val artworkUrl: String?,

    @ColumnInfo(name = "stream_url")
    val streamUrl: String,

    @Embedded(prefix = "author_")
    val author: Author
)
