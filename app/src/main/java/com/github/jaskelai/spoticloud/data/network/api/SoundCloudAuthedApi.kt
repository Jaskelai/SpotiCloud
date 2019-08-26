package com.github.jaskelai.spoticloud.data.network.api

import com.github.jaskelai.spoticloud.data.network.model.TrackSoundCloudResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SoundCloudAuthedApi {

    @GET("me/favorites")
    fun getFavoriteTracks(
        @Query("oauth_token") token: String
    ): Single<List<TrackSoundCloudResponse>>
}
