package com.github.kornilovmikhail.spoticloud.data.network.api

import com.github.kornilovmikhail.spoticloud.data.network.model.spotify.SearchedTracksSpotifyResponse
import com.github.kornilovmikhail.spoticloud.data.network.model.spotify.TracksSpotifyResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyAuthedApi {

    @GET("me/tracks")
    fun getFavoriteTracks(): Single<TracksSpotifyResponse>

    @GET("playlists/{id}/tracks")
    fun getTracksFromPlaylist(@Path("id") id: String): Single<TracksSpotifyResponse>

    @GET("search")
    fun searchForTracks(
        @Query("q") keyword: String,
        @Query("type") type: String
    ): Single<SearchedTracksSpotifyResponse>

    @PUT("me/tracks")
    fun addTrackToFav(
        @Query("ids") id: String
    ): Completable
}
