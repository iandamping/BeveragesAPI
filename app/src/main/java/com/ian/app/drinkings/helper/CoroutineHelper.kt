package com.ian.app.drinkings.helper

import kotlinx.coroutines.*

/**
 *
Created by Ian Damping on 27/05/2019.
Github = https://github.com/iandamping
 */

fun CoroutineScope.doSomethingIOScope(heavyFunction: suspend () -> Unit?) {
        this.launch {
            withContext(Dispatchers.IO) {
                heavyFunction()
            }
        }
}