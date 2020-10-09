package com.gabriel.unit_testing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class MainViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getBearerTokenUseCase: GetBearerTokenUseCase

    @Mock
    lateinit var observerStateModel: Observer<MainViewModel.SplashState>


    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var vm: MainViewModel

    @Before
    fun setUp() {
        vm = MainViewModel(
            getBearerTokenUseCase,
            coroutinesTestRule.testDispatcher
        )
    }

    @Test
    fun `when requesting bearer token on splash view model creation, retrieve token event  status is sent`() {
        runBlocking {
            vm.modelSplashState.observeForever(observerStateModel)

            verify(observerStateModel).onChanged(
                MainViewModel.SplashState.RetrieveBearerToken
            )
        }
    }

    @Test
    fun `when requesting bearer token, camera light-finder event is sent`() = runBlockingTest {
        vm.modelSplashState.observeForever(observerStateModel)

        vm.onRetrieveBearerToken()
        advanceTimeBy(5_000)

        verify(observerStateModel).onChanged(
            MainViewModel.SplashState.LightFinderCamera
        )

    }
}