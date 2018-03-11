package com.movies.careem.tmdb.app.core.injection

import com.movies.careem.tmdb.app.view.DiscoveryActivity
import com.movies.careem.tmdb.app.view.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppllicationModule {
    @ContributesAndroidInjector(modules = arrayOf(LoginActivity.InjectModule::class))
    abstract fun contributesLoginActivityInjection() : LoginActivity

    @ContributesAndroidInjector(modules = arrayOf(DiscoveryActivity.InjectModule::class))
    abstract fun contributesDiscoveryActivityInjection() : DiscoveryActivity
}