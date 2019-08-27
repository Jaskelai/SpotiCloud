package com.github.kornilovmikhail.spoticloud.data.network.api

import com.github.kornilovmikhail.spoticloud.data.network.model.TrackSoundCloudResponse
import io.reactivex.Single
import retrofit2.http.GET

interface SoundCloudAuthedApi {

    @GET("me/favorites")
    fun getFavoriteTracks(): Single<List<TrackSoundCloudResponse>>
}
