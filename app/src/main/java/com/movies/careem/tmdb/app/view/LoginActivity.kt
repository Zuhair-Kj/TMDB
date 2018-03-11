package com.movies.careem.tmdb.app.view

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.movies.careem.tmdb.R
import com.movies.careem.tmdb.app.core.api.calls.BaseApiCallback
import com.movies.careem.tmdb.app.core.api.calls.CreateSessionCall
import com.movies.careem.tmdb.app.core.injection.PerActivity
import com.movies.careem.tmdb.app.model.GuestSession
import com.movies.careem.tmdb.app.utils.SharedPreferencesUtils
import com.movies.careem.tmdb.databinding.ActivityLoginBinding
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjection
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    @Inject lateinit var sessionCall: CreateSessionCall
    @Inject lateinit var sharedPrefsUtils: SharedPreferencesUtils

    @Module
    abstract class InjectModule {

        @Binds
        @PerActivity
        abstract fun providesContext(activity: LoginActivity): Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AndroidInjection.inject(this)
        binding.title = "Hola!"
        binding.executePendingBindings()

        sessionCall.execute(object : BaseApiCallback<GuestSession>() {
            override fun onError(e: Throwable) {
                e.apply {
                    Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
                    printStackTrace()
                }
            }

            override fun onNext(session: GuestSession) {
                session.sessionId?.apply {
                    sharedPrefsUtils.storeSessionId(this)

                    val id = sharedPrefsUtils.getStoredSessionId()
                    Toast.makeText(this@LoginActivity, id, Toast.LENGTH_SHORT).show()
                    goToDiscovery()
                }
            }
        })
    }

    fun goToDiscovery() {
        startActivity(Intent(this, DiscoveryActivity::class.java))
    }

    override fun setContentView(layoutResID: Int) {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_login, null, false)
        setContentView(binding.root)
    }
}