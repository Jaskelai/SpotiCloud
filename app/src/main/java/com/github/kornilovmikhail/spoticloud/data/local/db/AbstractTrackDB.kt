package com.github.kornilovmikhail.spoticloud.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.kornilovmikhail.spoticloud.data.local.db.converter.StreamServiceConverter
import com.github.kornilovmikhail.spoticloud.data.local.db.dao.TrackDao
import com.github.kornilovmikhail.spoticloud.data.local.db.model.TrackDB

@Database(entities = [TrackDB::class], version = 1)
@TypeConverters(StreamServiceConverter::class)
abstract class AbstractTrackDB : RoomDatabase() {

    abstract fun trackDAO(): TrackDao
}
