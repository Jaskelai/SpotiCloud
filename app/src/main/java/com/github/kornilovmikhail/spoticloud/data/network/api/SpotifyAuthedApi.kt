package com.github.kornilovmikhail.spoticloud.data.network.api

import com.github.kornilovmikhail.spoticloud.data.network.model.spotify.TracksSpotifyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SpotifyAuthedApi {

    @GET("me/tracks")
    fun getFavoriteTracks(): Single<TracksSpotifyResponse>

    @GET("playlists/{id}/tracks")
    fun getTracksFromPlaylist(@Path("id") id: String): Single<TracksSpotifyResponse>
}
