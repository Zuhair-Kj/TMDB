package com.movies.careem.tmdb.app.core.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val baseUrl = "https://api.themoviedb.org/3/"

class RetrofitProvider {
    companion object {
        // TODO Inject this!!
        fun getRetrofit(): Retrofit =
                Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(OkHttpClient.Builder().build())
                        .baseUrl(baseUrl)
                        .client(getOkHttpBuuilder())
                        .build()

        fun getRemoteCLient(): RemoteClient =
                getRetrofit().create(RemoteClient::class.java)

        fun getOkHttpBuuilder(): OkHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }
}