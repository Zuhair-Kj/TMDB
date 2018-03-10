package com.movies.careem.tmdb.app

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.movies.careem.tmdb.R
import com.movies.careem.tmdb.app.core.api.calls.CreateSessionCall
import com.movies.careem.tmdb.app.model.GuestSession
import com.movies.careem.tmdb.databinding.ActivityLoginBinding
import io.reactivex.observers.DisposableObserver

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    lateinit var sessionCall: CreateSessionCall

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.title = "Hola!"
        binding.executePendingBindings()

        sessionCall = CreateSessionCall()
        sessionCall.execute(object : DisposableObserver<GuestSession>() {
            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
                e.apply {
                    Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
                    printStackTrace()
                }
            }

            override fun onNext(session: GuestSession) {
                Toast.makeText(this@LoginActivity, session.sessionId, Toast.LENGTH_SHORT).show()
            }

            override fun onStart() {
                super.onStart()
            }
        })
    }
}