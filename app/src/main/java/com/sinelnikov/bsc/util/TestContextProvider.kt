package com.sinelnikov.bsc.util

import kotlinx.coroutines.Dispatchers.Unconfined
import kotlin.coroutines.CoroutineContext

class TestContextProvider : CoroutineContextProvider() {
    override val Main: CoroutineContext = Unconfined
    override val IO: CoroutineContext = Unconfined
}