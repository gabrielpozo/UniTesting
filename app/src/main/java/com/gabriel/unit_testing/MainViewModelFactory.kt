package com.gabriel.unit_testing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers


class MainViewModelFactory(private val getBearerTokenUseCase: GetBearerTokenUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getBearerTokenUseCase, Dispatchers.Main) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}