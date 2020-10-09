package com.gabriel.unit_testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val viewModelFactory = MainViewModelFactory(
        GetBearerTokenUseCase()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.modelSplashState.observe(this, Observer {
            when (it) {
                is MainViewModel.SplashState.LightFinderCamera -> {

                }
                is MainViewModel.SplashState.RetrieveBearerToken -> {
                    viewModel.onRetrieveBearerToken()
                }
            }

        })
    }
}