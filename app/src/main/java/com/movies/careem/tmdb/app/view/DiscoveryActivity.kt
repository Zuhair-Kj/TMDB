package com.movies.careem.tmdb.app.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.movies.careem.tmdb.R
import com.movies.careem.tmdb.app.core.api.calls.BaseApiCallback
import com.movies.careem.tmdb.app.core.api.calls.DiscoveryCall
import com.movies.careem.tmdb.app.core.injection.PerActivity
import com.movies.careem.tmdb.app.model.MovieCollection
import com.movies.careem.tmdb.app.model.MovieMetaData
import com.movies.careem.tmdb.app.utils.SharedPreferencesUtils
import com.movies.careem.tmdb.databinding.ActivityDiscoveryBinding
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjection
import javax.inject.Inject

class DiscoveryActivity : AppCompatActivity() {

    lateinit var binding: ActivityDiscoveryBinding
    @Inject lateinit var discoveryCall: DiscoveryCall
    @Inject lateinit var sharedPrefsUtils: SharedPreferencesUtils

    @Module
    class InjectModule {

        @Provides
        @PerActivity
        internal fun providesContext(activity: DiscoveryActivity): Context = activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discovery)
        binding.title.text = "Hola Lola"
        AndroidInjection.inject(this)

        discoveryCall.execute(object : BaseApiCallback<MovieCollection>() {

            override fun onStart() {
                binding.title.text = "Started"
            }

            override fun onNext(value: MovieCollection) {
                binding.title.text = ""
                value.results.forEach {
                    binding.title.append("\n" + it.title)
                }
            }

            override fun onError(e: Throwable) {
                binding.title.text = e.message
                e.printStackTrace()
            }
        })
    }

    override fun setContentView(layoutResID: Int) {
        binding = DataBindingUtil.inflate(layoutInflater, layoutResID, null, false)
        setContentView(binding.root)
    }
}