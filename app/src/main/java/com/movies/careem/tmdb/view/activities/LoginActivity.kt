package com.movies.careem.tmdb.view.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.movies.careem.tmdb.R
import com.movies.careem.tmdb.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding :ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.title = "Hola!"
        binding.executePendingBindings()
    }
}