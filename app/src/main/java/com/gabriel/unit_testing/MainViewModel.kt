package com.gabriel.unit_testing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class MainViewModel(
    private val getBearerTokenUseCase: GetBearerTokenUseCase,
    private val testCoroutineDispatcher: TestCoroutineDispatcher
) : BaseViewModel(Dispatchers.Main) {

    sealed class SplashState {
        object RetrieveBearerToken : SplashState()
        object LightFinderCamera : SplashState()
    }

    val modelSplashState: LiveData<SplashState>
        get() = _modelSplashState
    private val _modelSplashState = MutableLiveData<SplashState>()

    init {
        _modelSplashState.value = SplashState.RetrieveBearerToken
    }

    fun onRetrieveBearerToken() {
        launch(testCoroutineDispatcher) {
            getBearerTokenUseCase.execute()
            delay(4000)
            _modelSplashState.value = SplashState.LightFinderCamera
        }
    }
}