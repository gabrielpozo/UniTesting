package com.gabriel.unit_testing

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface Scope : CoroutineScope {

    class Impl(override val uiDispatcher: CoroutineDispatcher) :
        Scope {
        override lateinit var job: Job
    }

    var job: Job
    val uiDispatcher: CoroutineDispatcher

    override val coroutineContext: CoroutineContext
        get() = uiDispatcher + job

    fun initScope() {
        job = SupervisorJob()
    }

    fun cancelRequestScope() {
        coroutineContext.cancelChildren()
    }
}