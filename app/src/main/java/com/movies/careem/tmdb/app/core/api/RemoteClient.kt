package com.movies.careem.tmdb.app.core.api

import com.movies.careem.tmdb.app.model.GuestSession
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface RemoteClient {

    @GET("/3/authentication/guest_session/new")
    fun createGuestSession(@Query("api_key") url: String): Single<GuestSession>

}