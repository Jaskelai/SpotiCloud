package com.github.kornilovmikhail.spoticloud.data.di

import com.github.kornilovmikhail.spoticloud.BuildConfig
import com.github.kornilovmikhail.spoticloud.data.network.api.SoundCloudApi
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class NetworkModule {

    companion object {
        private const val SOUNDCLOUD_URL = "SOUNDCLOUD_URL"
        private const val RETROFIT_SOUNDCLOUD = "RETROFIT_SOUNDLOUD"
    }

    @Provides
    @AppScope
    @Named(SOUNDCLOUD_URL)
    fun provideSoundcloudBaseUrl(): String = BuildConfig.SOUNDCLOUD_URL

    @Provides
    @AppScope
    @Named(RETROFIT_SOUNDCLOUD)
    fun provideRetrofitSoundCloud(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        @Named(SOUNDCLOUD_URL) baseURL: String
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(rxJava2CallAdapterFactory)
        .build()

    @Provides
    @AppScope
    fun provideSoundcloudApi(@Named(RETROFIT_SOUNDCLOUD) retrofit: Retrofit): SoundCloudApi =
        retrofit.create(SoundCloudApi::class.java)

    @Provides
    @AppScope
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @AppScope
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()
}
