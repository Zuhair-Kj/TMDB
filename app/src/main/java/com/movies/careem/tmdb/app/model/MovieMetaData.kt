package com.movies.careem.tmdb.app.model

import com.google.gson.annotations.SerializedName

class MovieMetaData constructor(val id: String?,
                                val title: String?,
                                @SerializedName("vote_average") val votes: Float,
                                val adult: Boolean)