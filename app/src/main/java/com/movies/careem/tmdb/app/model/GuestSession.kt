package com.movies.careem.tmdb.app.model

import com.google.gson.annotations.SerializedName


class GuestSession constructor(@SerializedName("expires_at") val expiryDate: String?,
                               @SerializedName("guest_session_id") val sessionId: String?) {
    fun isExpired() : Boolean = false
}