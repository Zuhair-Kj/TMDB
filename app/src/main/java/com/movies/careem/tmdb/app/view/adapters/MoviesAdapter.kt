package com.movies.careem.tmdb.app.view.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.movies.careem.tmdb.R
import com.movies.careem.tmdb.app.model.MovieMetaData
import com.movies.careem.tmdb.databinding.ListItemMediaBinding

class MoviesAdapter constructor(val items: MutableList<MovieMetaData>) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.populateCard(items.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = DataBindingUtil.inflate<ListItemMediaBinding>(
                LayoutInflater.from(parent.context),
                R.layout.list_item_media,
                parent,
                false)

        return MovieViewHolder(binding.cardView)
    }

    override fun getItemCount(): Int = items.size

    class MovieViewHolder constructor(cardView: CardView) : RecyclerView.ViewHolder(cardView) {
        val listItembinding: ListItemMediaBinding

        init {
            listItembinding = DataBindingUtil.bind(cardView)
        }

        fun populateCard(movie: MovieMetaData) {
            listItembinding.movie = movie
            listItembinding.executePendingBindings()
        }
    }
}