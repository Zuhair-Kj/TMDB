package com.movies.careem.tmdb.app.core.api

import com.movies.careem.tmdb.app.model.GuestSession
import com.movies.careem.tmdb.app.model.MovieCollection
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

const val KEY_API_KEY: String = "api_key"
const val KEY_PAGE: String = "page"

interface RemoteClient {
    @GET("/3/authentication/guest_session/new")
    fun createGuestSession(@Query(KEY_API_KEY) token: String): Single<GuestSession>

    @GET("/3/discover/movie")
    fun getMovies(@Query(KEY_PAGE) page: Int, @Query(KEY_API_KEY) token: String): Single<MovieCollection>
}