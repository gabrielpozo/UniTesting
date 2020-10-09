package com.gabriel.unit_testing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.test.TestCoroutineDispatcher

class MainViewModelFactory(private val getBearerTokenUseCase: GetBearerTokenUseCase, private val testCoroutineDispatcher: TestCoroutineDispatcher) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getBearerTokenUseCase, testCoroutineDispatcher) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}