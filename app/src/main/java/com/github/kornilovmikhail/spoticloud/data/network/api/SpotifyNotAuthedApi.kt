package com.github.kornilovmikhail.spoticloud.data.network.api

import com.github.kornilovmikhail.spoticloud.data.network.model.TokenResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface SpotifyNotAuthedApi {

    @FormUrlEncoded
    @POST("token")
    fun getToken(
        @Field("grant_type") grantType: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String,
        @Header("Authorization") authorization: String
    ): Single<TokenResponse>
}
