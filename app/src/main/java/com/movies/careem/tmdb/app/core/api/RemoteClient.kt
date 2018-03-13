package com.movies.careem.tmdb.app.core.api

import com.movies.careem.tmdb.app.model.GuestSession
import com.movies.careem.tmdb.app.model.MovieCollection
import com.movies.careem.tmdb.app.model.MovieMetaData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface RemoteClient {

    @GET("/3/authentication/guest_session/new")
    fun createGuestSession(@Query("api_key") token: String): Single<GuestSession>

    @GET("/3/discover/movie")
    fun getMovies(@Query("api_key") token: String): Single<MovieCollection>

}