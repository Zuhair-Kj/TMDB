package com.movies.careem.tmdb.app.core.injection

import com.movies.careem.tmdb.app.view.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppllicationModule {
    @ContributesAndroidInjector
    abstract fun contributesActivityInjection() : LoginActivity
}