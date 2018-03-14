package com.movies.careem.tmdb.app.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.movies.careem.tmdb.R
import com.movies.careem.tmdb.app.core.api.calls.BaseApiCallback
import com.movies.careem.tmdb.app.core.api.calls.DiscoveryCall
import com.movies.careem.tmdb.app.core.injection.PerActivity
import com.movies.careem.tmdb.app.model.MovieCollection
import com.movies.careem.tmdb.app.view.adapters.MoviesAdapter
import com.movies.careem.tmdb.databinding.ActivityDiscoveryBinding
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjection
import javax.inject.Inject

class DiscoveryActivity : AppCompatActivity() {

    lateinit var binding: ActivityDiscoveryBinding
    @Inject lateinit var discoveryCall: DiscoveryCall
    lateinit var moviesAdapter: MoviesAdapter
    var onScrollListener: RecyclerView.OnScrollListener

    init {
        onScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0) return
                //not interested in scroll ups
                val lastPosition = (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                // Triggers an event when you are about oto reach the bottom of the page.
                if (lastPosition >= moviesAdapter.itemCount - 3) {
                    callApiAndUpdateAdapter(moviesAdapter.nextPageNumber++)
                }
            }
        }
    }

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
        binding.list.addOnScrollListener(onScrollListener)

        callApiAndUpdateAdapter()

    }

    override fun setContentView(layoutResID: Int) {
        binding = DataBindingUtil.inflate(layoutInflater, layoutResID, null, false)
        setContentView(binding.root)
    }

    override fun onStop() {
        super.onStop()
        discoveryCall.unsubscribe()
    }

    fun callApiAndUpdateAdapter(pageNumber: Int = 1) {
        discoveryCall.pageNumber = pageNumber
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