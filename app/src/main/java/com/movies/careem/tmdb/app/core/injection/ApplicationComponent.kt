package com.movies.careem.tmdb.app.core.injection

import com.movies.careem.tmdb.app.MyApplication
import com.movies.careem.tmdb.app.view.LoginActivity
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component (modules = arrayOf(AndroidInjectionModule::class,
        CoreModule::class,
        AppllicationModule::class,
        LoginActivity.InjectModule::class))
interface ApplicationComponent {
    fun inject(application: MyApplication)
}