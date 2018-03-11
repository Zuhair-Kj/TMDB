package com.movies.careem.tmdb.app.utils

import android.content.Context
import com.movies.careem.tmdb.app.core.injection.PerActivity
import javax.inject.Inject

class SharedPreferencesUtils @Inject constructor(@PerActivity val context: Context) {
    fun storeSessionId(sessionId: String) {
        context
                .getSharedPreferences("Prefs", Context.MODE_PRIVATE)
                .edit()
                .putString("SessionId", sessionId)
                .apply()
    }

    fun getStoredSessionId(): String {
        return context.getSharedPreferences("Prefs", Context.MODE_PRIVATE)
                .getString("SessionId", "")
    }

}