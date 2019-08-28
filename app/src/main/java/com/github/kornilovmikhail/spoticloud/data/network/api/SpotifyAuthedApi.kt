package com.github.kornilovmikhail.spoticloud.data.network.api

import com.github.kornilovmikhail.spoticloud.data.network.model.spotify.TracksSpotifyResponse
import io.reactivex.Single
import retrofit2.http.GET

interface SpotifyAuthedApi {

    @GET("me/tracks")
    fun getFavoriteTracks(): Single<TracksSpotifyResponse>
}
