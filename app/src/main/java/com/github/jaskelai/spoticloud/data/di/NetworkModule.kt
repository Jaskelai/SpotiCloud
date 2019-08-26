package com.github.jaskelai.spoticloud.data.di

import com.github.jaskelai.spoticloud.BuildConfig
import com.github.jaskelai.spoticloud.data.TokenHelperSoundcloud
import com.github.jaskelai.spoticloud.data.network.api.SoundCloudAuthedApi
import com.github.jaskelai.spoticloud.data.network.api.SoundCloudNotAuthedApi
import com.github.jaskelai.spoticloud.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class NetworkModule {

    companion object {
        private const val SOUNDCLOUD_URL = "soundcloud_url"
        private const val RETROFIT_SOUNDCLOUD_NOT_AUTHED = "retrofit_soundcloud_no_authed"
        private const val RETROFIT_SOUNDCLOUD_AUTHED = "retrofit_soundcloud_authed"
    }

    @Provides
    @AppScope
    @Named(SOUNDCLOUD_URL)
    fun provideSoundcloudBaseUrl(): String = BuildConfig.SOUNDCLOUD_URL

    @Provides
    @AppScope
    @Named(RETROFIT_SOUNDCLOUD_NOT_AUTHED)
    fun provideRetrofitNotAuthedSoundCloud(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        @Named(RETROFIT_SOUNDCLOUD_NOT_AUTHED) okHttpClient: OkHttpClient,
        @Named(SOUNDCLOUD_URL) baseURL: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
    }

    @Provides
    @AppScope
    @Named(RETROFIT_SOUNDCLOUD_NOT_AUTHED)
    fun provideOkHttpNotAuthedSoundCoud(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @AppScope
    fun provideSoundcloudNotAuthedApi(@Named(RETROFIT_SOUNDCLOUD_NOT_AUTHED) retrofit: Retrofit): SoundCloudNotAuthedApi {
        return retrofit.create(SoundCloudNotAuthedApi::class.java)
    }

    @Provides
    @AppScope
    @Named(RETROFIT_SOUNDCLOUD_AUTHED)
    fun provideRetrofitAuthedSoundCloud(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        @Named(RETROFIT_SOUNDCLOUD_AUTHED) clientOkHttpClient: OkHttpClient,
        @Named(SOUNDCLOUD_URL) baseURL: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(clientOkHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
    }

    @Provides
    @AppScope
    @Named(RETROFIT_SOUNDCLOUD_AUTHED)
    fun provideOkHttpAuthedSoundCloud(
        @Named(RETROFIT_SOUNDCLOUD_AUTHED) authInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @AppScope
    @Named(RETROFIT_SOUNDCLOUD_AUTHED)
    fun provideSoundCloudInterceptor(
        tokenHelper: TokenHelperSoundcloud
    ): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()

            val url = originalRequest.url().newBuilder()
                .addQueryParameter("oauth_token", tokenHelper.getToken())
                .build()

            val newRequest = originalRequest.newBuilder()
                .url(url)
                .build()

            chain.proceed(newRequest)
        }
    }

    @Provides
    @AppScope
    fun provideSoundcloudAuthedApi(@Named(RETROFIT_SOUNDCLOUD_AUTHED) retrofit: Retrofit): SoundCloudAuthedApi {
        return retrofit.create(SoundCloudAuthedApi::class.java)
    }

    @Provides
    @AppScope
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @AppScope
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }
}
