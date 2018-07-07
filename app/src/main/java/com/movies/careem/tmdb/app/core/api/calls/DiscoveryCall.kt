package com.movies.careem.tmdb.app.core.api.calls

import com.movies.careem.tmdb.app.core.api.ApiEngine
import com.movies.careem.tmdb.app.model.MovieCollection
import io.reactivex.Observable
import javax.inject.Inject

class DiscoveryCall @Inject constructor(apiEngine: ApiEngine) : BaseApiCall<MovieCollection>(apiEngine) {

    var pageNumber = 1

    override fun buildObservable(): Observable<MovieCollection> = apiEngine.getMovies(pageNumber).toObservable()
}
