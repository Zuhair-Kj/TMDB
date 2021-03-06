package com.movies.careem.tmdb.app.core.api

import com.movies.careem.tmdb.app.Config
import com.movies.careem.tmdb.app.model.GuestSession
import com.movies.careem.tmdb.app.model.MovieCollection
import io.reactivex.Single
import javax.inject.Inject

class ApiEngine @Inject constructor(val remoteClient: RemoteClient) {
    fun getGuestToken(): Single<GuestSession> = remoteClient.createGuestSession(Config.apiKey)

    fun getMovies(pageNumber: Int): Single<MovieCollection> = remoteClient.getMovies(pageNumber, Config.apiKey)
}