package com.movies.careem.tmdb.app.model

import com.google.gson.annotations.SerializedName
import com.movies.careem.tmdb.app.Config

class MovieMetaData constructor(val id: String?,
                                val title: String?,
                                @SerializedName("vote_average") val votes: Float,
                                val adult: Boolean,
                                @SerializedName("poster_path") val imageUrl: String) {
    fun getImageFullUrl(): String = Config.imageBaseUrl + imageUrl
}