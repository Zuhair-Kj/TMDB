package com.movies.careem.tmdb.app.core.api.calls;

import io.reactivex.observers.DisposableObserver;

public class BaseApiCallback<T> extends DisposableObserver<T>{
    @Override
    protected void onStart() {
    }

    @Override
    public void onNext(T value) {
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
}
