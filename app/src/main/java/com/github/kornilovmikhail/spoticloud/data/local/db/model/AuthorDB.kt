package com.github.kornilovmikhail.spoticloud.data.local.db.model

import androidx.room.Entity

@Entity
data class AuthorDB (
    val name: String,

    val uri: String,

    val avatarUrl: String
)
