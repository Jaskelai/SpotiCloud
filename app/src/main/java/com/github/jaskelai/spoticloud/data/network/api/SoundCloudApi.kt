package com.github.jaskelai.spoticloud.data.network.api

import com.github.jaskelai.spoticloud.data.network.model.TokenSoundcloudResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SoundCloudApi {

    @FormUrlEncoded
    @POST("oauth2/token")
    fun getToken(
        @Field("username") email: String,
        @Field("password") password: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String
    ): Single<TokenSoundcloudResponse>
}
