package com.github.kornilovmikhail.spoticloud.data.network.api

import com.github.kornilovmikhail.spoticloud.data.network.model.soundcloud.TrackSoundCloudResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface SoundCloudAuthedApi {

    @GET("me/favorites")
    fun getFavoriteTracks(): Single<List<TrackSoundCloudResponse>>

    @GET("tracks/{id}")
    fun getTrack(@Path("id") id: String): Single<TrackSoundCloudResponse>

    @GET("tracks")
    fun searchForTracks(
        @Query("q") text: String
    ): Single<List<TrackSoundCloudResponse>>

    @PUT("me/favorites/{id}")
    fun addTrackToFav(
        @Path("id") id: String
    ): Completable
}
