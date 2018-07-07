package com.movies.careem.tmdb.app.core.injection

import com.movies.careem.tmdb.app.MyApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,
    CoreModule::class,
    AppllicationModule::class))
interface ApplicationComponent {
    fun inject(application: MyApplication)
}