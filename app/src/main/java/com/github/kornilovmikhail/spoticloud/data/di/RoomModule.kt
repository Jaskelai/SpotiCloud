package com.github.kornilovmikhail.spoticloud.data.di

import android.content.Context
import androidx.room.Room
import com.github.kornilovmikhail.spoticloud.data.local.db.AbstractTrackDB
import com.github.kornilovmikhail.spoticloud.data.local.db.dao.TrackDao
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    companion object {
        private const val DATABASE_NAME = "tracks_db.db"
    }

    @Provides
    @AppScope
    fun provideTrackDB(context: Context): AbstractTrackDB =
        Room.databaseBuilder(context, AbstractTrackDB::class.java, DATABASE_NAME)
            .build()

    @Provides
    @AppScope
    fun provideTrackDAO(database: AbstractTrackDB): TrackDao = database.trackDAO()
}
