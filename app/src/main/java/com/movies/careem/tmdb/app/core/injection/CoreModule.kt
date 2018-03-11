package com.movies.careem.tmdb.app.core.injection

import com.movies.careem.tmdb.app.Config
import com.movies.careem.tmdb.app.core.api.ApiEngine
import com.movies.careem.tmdb.app.core.api.RemoteClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class CoreModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }

    @Singleton
    @Provides
    fun providesRemoteClient(okHttpClient: OkHttpClient) =
            buildRetrofit(okHttpClient, Config.baseUrl)
                    .create(RemoteClient::class.java)

    @Singleton
    @Provides
    fun providesApiEngine(remoteClient: RemoteClient) = ApiEngine(remoteClient)

    private fun buildRetrofit(okhttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().build())
                .client(okhttpClient)
                .baseUrl(baseUrl)
                .build()
    }
}