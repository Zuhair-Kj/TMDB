package com.movies.careem.tmdb.app.core.api.calls

import com.movies.careem.tmdb.app.core.api.ApiEngine
import com.movies.careem.tmdb.app.model.GuestSession
import io.reactivex.Observable
import javax.inject.Inject

class CreateSessionCall @Inject constructor(apiEngine: ApiEngine) : BaseApiCall<GuestSession>(apiEngine) {

    // TODO improve so that you don't create a token if it is not expired ..or about to.
    override fun buildObservable(): Observable<GuestSession> {
        return apiEngine.getGuestToken().toObservable()
    }
}