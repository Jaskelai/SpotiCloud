package com.github.kornilovmikhail.spoticloud.data.network.api

import com.github.kornilovmikhail.spoticloud.data.network.model.soundcloud.TrackSoundCloudResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SoundCloudAuthedApi {

    @GET("me/favorites")
    fun getFavoriteTracks(): Single<List<TrackSoundCloudResponse>>

    @GET("tracks/{id}")
    fun getTrack(@Path("id") id: String): Single<TrackSoundCloudResponse>
}
