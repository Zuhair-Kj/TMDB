package com.movies.careem.tmdb.app.core.api.calls

import com.movies.careem.tmdb.app.core.api.ApiEngine
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class BaseApiCall<T> constructor(
    val apiEngine: ApiEngine,
    var subscribeOn: Scheduler = Schedulers.io(),
    var observeOn: Scheduler = AndroidSchedulers.mainThread()
) {

    private val compositeObserver = CompositeDisposable()

    protected abstract fun buildObservable(): Observable<T>

    fun execute(callback: BaseApiCallback<T>) {
        compositeObserver.add(
            buildObservable()
                .compose(applySchedulers())
                .subscribeWith(callback)
        )
    }

    private fun applySchedulers(
        subscribeOn: Scheduler = this.subscribeOn,
        observeOn: Scheduler = this.observeOn
    ): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(subscribeOn)
                .unsubscribeOn(subscribeOn)
                .observeOn(observeOn)
        }
    }

    fun unsubscribe() = compositeObserver.dispose()
}