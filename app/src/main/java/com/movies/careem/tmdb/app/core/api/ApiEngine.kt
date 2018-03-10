package com.movies.careem.tmdb.app.core.api

import com.movies.careem.tmdb.app.Config
import com.movies.careem.tmdb.app.model.GuestSession
import io.reactivex.*

class ApiEngine {

    var remoteClient: RemoteClient

    companion object {
        private val singleInstance = ApiEngine()
        fun getInstance(): ApiEngine {
            return singleInstance
        }
    }

    private constructor() {
        remoteClient = RetrofitProvider.getRemoteCLient()
    }

    fun getGuestToken(): Single<GuestSession> = remoteClient.createGuestSession(Config.apiKey)
}