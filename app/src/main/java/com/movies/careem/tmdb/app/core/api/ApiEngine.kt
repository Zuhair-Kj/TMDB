package com.movies.careem.tmdb.app.core.api

import com.movies.careem.tmdb.app.Config
import com.movies.careem.tmdb.app.model.GuestSession
import io.reactivex.*
import javax.inject.Inject

class ApiEngine @Inject constructor(val remoteClient: RemoteClient) {
    fun getGuestToken(): Single<GuestSession> = remoteClient.createGuestSession(Config.apiKey)
}