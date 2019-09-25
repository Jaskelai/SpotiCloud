package com.github.kornilovmikhail.spoticloud.data.network.di

import com.github.kornilovmikhail.spoticloud.BuildConfig
import com.github.kornilovmikhail.spoticloud.data.network.api.*
import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import com.github.kornilovmikhail.spoticloud.domain.interfaces.TokenHelper
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
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
        const val AUTH_SPOTIFY_HEADER = "Authorization"
        const val AUTH_SPOTIFY_HEADER_EXTRA = "Bearer"
        const val AUTH_SOUNDCLOUD_QUERY = "oauth_token"

        private const val SOUNDCLOUD_URL = "soundcloud_url"
        private const val SOUNDCLOUD_V2_URL = "soundcloud_v2_url"
        private const val SPOTIFY_URL = "spotify_url"
        private const val SPOTIFY_ACCOUNTS_URL = "spotify_accounts_url"
        private const val BASE_HTTPCLIENT = "base_httpclient"
        private const val RETROFIT_SOUNDCLOUD_NOT_AUTHED = "retrofit_soundcloud_no_authed"
        private const val RETROFIT_SOUNDCLOUD_AUTHED = "retrofit_soundcloud_authed"
        private const val RETROFIT_SOUNDCLOUD_V2_AUTHED = "retrofit_soundcloud_v2_authed"
        private const val RETROFIT_SPOTIFY_AUTHED = "retrofit_spotify_authed"
        private const val RETROFIT_SPOTIFY_NOT_AUTHED = "retrofit_spotify_no_authed"
    }

    @Provides
    @AppScope
    @Named(BASE_HTTPCLIENT)
    fun provideBaseOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
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
        @Named(BASE_HTTPCLIENT) okHttpClient: OkHttpClient,
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
        @Named(RETROFIT_SOUNDCLOUD_AUTHED) authInterceptor: Interceptor,
        @SoundCloudQualifier soundCloudAuthenticator: Authenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .authenticator(soundCloudAuthenticator)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @AppScope
    @Named(RETROFIT_SOUNDCLOUD_AUTHED)
    fun provideSoundCloudAuthedInterceptor(
        @SoundCloudQualifier tokenHelperSoundcloud: TokenHelper
    ): Interceptor {
        return Interceptor {
            val originalRequest = it.request()

            val url = originalRequest.url.newBuilder()
                .addQueryParameter(AUTH_SOUNDCLOUD_QUERY, tokenHelperSoundcloud.getToken())
                .build()

            val newRequest = originalRequest.newBuilder()
                .url(url)
                .build()

            it.proceed(newRequest)
        }
    }

    @Provides
    @AppScope
    fun provideSoundcloudAuthedApi(@Named(RETROFIT_SOUNDCLOUD_AUTHED) retrofit: Retrofit): SoundCloudAuthedApi {
        return retrofit.create(SoundCloudAuthedApi::class.java)
    }

    @Provides
    @AppScope
    @Named(SOUNDCLOUD_V2_URL)
    fun provideSoundcloudV2BaseUrl(): String = BuildConfig.SOUNDCLOUD_URL_V2

    @Provides
    @AppScope
    @Named(RETROFIT_SOUNDCLOUD_V2_AUTHED)
    fun provideRetrofitAuthedV2SoundCloud(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        @Named(RETROFIT_SOUNDCLOUD_AUTHED) clientOkHttpClient: OkHttpClient,
        @Named(SOUNDCLOUD_V2_URL) baseURL: String
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
    fun provideSoundCloudV2AuthedApi(
        @Named(RETROFIT_SOUNDCLOUD_V2_AUTHED) retrofit: Retrofit
    ): SoundCloudV2AuthedApi {
        return retrofit.create(SoundCloudV2AuthedApi::class.java)
    }

    @Provides
    @AppScope
    @Named(SPOTIFY_ACCOUNTS_URL)
    fun provideSpotifyAccountsBaseUrl(): String = BuildConfig.SPOTIFY_ACCOUNTS_URL

    @Provides
    @AppScope
    @Named(RETROFIT_SPOTIFY_NOT_AUTHED)
    fun provideRetrofitNotAuthedSpotify(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        @Named(BASE_HTTPCLIENT) okHttpClient: OkHttpClient,
        @Named(SPOTIFY_ACCOUNTS_URL) baseURL: String
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
    fun provideSpotifyNotAuthedApi(@Named(RETROFIT_SPOTIFY_NOT_AUTHED) retrofit: Retrofit): SpotifyNotAuthedApi {
        return retrofit.create(SpotifyNotAuthedApi::class.java)
    }

    @Provides
    @AppScope
    @Named(SPOTIFY_URL)
    fun provideSpotifyBaseUrl(): String = BuildConfig.SPOTIFY_URL

    @Provides
    @AppScope
    @Named(RETROFIT_SPOTIFY_AUTHED)
    fun provideRetrofitAuthedSpotify(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        @Named(RETROFIT_SPOTIFY_AUTHED) clientOkHttpClient: OkHttpClient,
        @Named(SPOTIFY_URL) baseURL: String
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
    @Named(RETROFIT_SPOTIFY_AUTHED)
    fun provideOkHttpAuthedSpotify(
        @Named(RETROFIT_SPOTIFY_AUTHED) authInterceptor: Interceptor,
        @SpotifyQualifier spotifyAuthenticator: Authenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .authenticator(spotifyAuthenticator)
            .build()
    }

    @Provides
    @AppScope
    @Named(RETROFIT_SPOTIFY_AUTHED)
    fun provideSpotifyAuthedInterceptor(
        @SpotifyQualifier tokenHelperSpotify: TokenHelper
    ): Interceptor {
        return Interceptor {
            var request = it.request()

            val token = tokenHelperSpotify.getToken()

            token?.let { token ->
                request = request.newBuilder()
                    .addHeader(AUTH_SPOTIFY_HEADER, "$AUTH_SPOTIFY_HEADER_EXTRA $token")
                    .build()
            }
            it.proceed(request)
        }
    }

    @Provides
    @AppScope
    fun provideSpotifyAuthedApi(@Named(RETROFIT_SPOTIFY_AUTHED) retrofit: Retrofit): SpotifyAuthedApi {
        return retrofit.create(SpotifyAuthedApi::class.java)
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
