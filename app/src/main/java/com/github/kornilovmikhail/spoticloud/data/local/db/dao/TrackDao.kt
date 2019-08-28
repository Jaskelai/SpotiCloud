package com.github.kornilovmikhail.spoticloud.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Transaction
import com.github.kornilovmikhail.spoticloud.data.local.db.model.TrackDB
import com.github.kornilovmikhail.spoticloud.domain.model.StreamServiceEnum
import io.reactivex.Single

@Dao
interface TrackDao {

    @Query("SELECT * FROM track")
    fun findAllTracks(): Single<List<TrackDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTrackList(tracks: List<TrackDB>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTrack(track: TrackDB)

    @Query("DELETE FROM track")
    fun deleteAllTracks()

    @Query("DELETE FROM track WHERE stream_service = :service")
    fun deleteTracksByStreamService(service: StreamServiceEnum)

    @Query("SELECT * FROM track WHERE stream_service = :service")
    fun findTracksByStreamService(service: StreamServiceEnum): Single<List<TrackDB>>

    @Transaction
    fun upsertSoundcloudTracks(tracks: List<TrackDB>) {
        deleteTracksByStreamService(StreamServiceEnum.SOUNDCLOUD)
        insertTrackList(tracks)
    }

    @Transaction
    fun upsertSpotifyTracks(tracks: List<TrackDB>) {
        deleteTracksByStreamService(StreamServiceEnum.SPOTIFY)
        insertTrackList(tracks)
    }
}
