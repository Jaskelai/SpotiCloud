package com.github.kornilovmikhail.spoticloud.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Transaction
import com.github.kornilovmikhail.spoticloud.data.local.db.model.TrackDB
import io.reactivex.Single

@Dao
interface TrackDao {

    @Query("SELECT * FROM track")
    fun findAllTracks(): Single<List<TrackDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTrackList(tracks: List<TrackDB>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTrack(track: TrackDB)

    @Query("DELETE FROM track")
    fun deleteAllTracks()

    @Transaction
    fun upsert(tracks: List<TrackDB>) {
        deleteAllTracks()
        insertTrackList(tracks)
    }
}
