package com.movies.careem.tmdb.app.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.movies.careem.tmdb.R
import com.movies.careem.tmdb.app.core.api.calls.BaseApiCallback
import com.movies.careem.tmdb.app.core.api.calls.DiscoveryCall
import com.movies.careem.tmdb.app.core.injection.PerActivity
import com.movies.careem.tmdb.app.model.MovieCollection
import com.movies.careem.tmdb.app.model.MovieMetaData
import com.movies.careem.tmdb.app.utils.SharedPreferencesUtils
import com.movies.careem.tmdb.app.view.adapters.MoviesAdapter
import com.movies.careem.tmdb.databinding.ActivityDiscoveryBinding
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjection
import javax.inject.Inject

class DiscoveryActivity : AppCompatActivity() {

    lateinit var binding: ActivityDiscoveryBinding
    @Inject lateinit var discoveryCall: DiscoveryCall
    @Inject lateinit var sharedPrefsUtils: SharedPreferencesUtils
    lateinit var moviesAdapter: MoviesAdapter

    @Module
    class InjectModule {

        @Provides
        @PerActivity
        internal fun providesContext(activity: DiscoveryActivity): Context = activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discovery)
        AndroidInjection.inject(this)

        binding.list.layoutManager = GridLayoutManager(this, 3)
        moviesAdapter = MoviesAdapter(mutableListOf())
        binding.list.adapter = moviesAdapter

        callApiAndUpdateAdapter()

    }

    override fun setContentView(layoutResID: Int) {
        binding = DataBindingUtil.inflate(layoutInflater, layoutResID, null, false)
        setContentView(binding.root)
    }

    fun callApiAndUpdateAdapter() {
        discoveryCall.execute(object : BaseApiCallback<MovieCollection>() {

            override fun onNext(movieCollection: MovieCollection) {
                moviesAdapter.items.addAll(movieCollection.results)
                moviesAdapter.notifyDataSetChanged()
            }

            override fun onError(e: Throwable) {
                Toast.makeText(this@DiscoveryActivity, e.message, Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        })
    }
}