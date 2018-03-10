package com.movies.careem.tmdb.app.core.api.calls

import com.movies.careem.tmdb.app.model.GuestSession
import io.reactivex.Observable

class CreateSessionCall : BaseApiCall<GuestSession>() {

    override fun buildObservable(): Observable<GuestSession> {
        return apiEngine.getGuestToken().toObservable()
    }
}