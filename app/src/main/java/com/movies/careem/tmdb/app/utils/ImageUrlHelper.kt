package com.movies.careem.tmdb.app.utils

import android.content.Context
import com.movies.careem.tmdb.R
import com.movies.careem.tmdb.app.Config

class ImageUrlHelper {
    companion object {
        fun getImageFullUrl(context: Context, imageName: String): String =
                String.format("%s%d%s", Config.imageBaseUrl, getImageConfiguration(context), imageName)
        // example https://image.tmdb.org/t/p/w185/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg

        private fun getImageConfiguration(context: Context): Int {
            return context.resources.getInteger(R.integer.poster_config_size)
        }
    }
}