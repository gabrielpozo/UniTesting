package com.gabriel.unit_testing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(
    private val getBearerTokenUseCase: GetBearerTokenUseCase,
    uiDispatcher: CoroutineDispatcher
) : BaseViewModel(uiDispatcher) {

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
        launch {
            getBearerTokenUseCase.execute()
            delay(4000)
            _modelSplashState.value = SplashState.LightFinderCamera
        }
    }
}