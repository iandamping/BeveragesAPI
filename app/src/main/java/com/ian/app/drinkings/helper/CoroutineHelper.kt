package com.ian.app.drinkings.helper

import kotlinx.coroutines.*

/**
 *
Created by Ian Damping on 27/05/2019.
Github = https://github.com/iandamping
 */

inline fun CoroutineScope.doSomethingWithIOScope(crossinline heavyFunction: suspend () -> Unit?) {
    this.launch {
        withContext(Dispatchers.IO) {
            heavyFunction()
        }
    }
}

inline fun <reified T> CoroutineScope.doSomethingWithDeferred(deferred: Deferred<T>,
                                                              crossinline onSuccess: (T) -> Unit,
                                                              crossinline onFailed: (String) -> Unit
) {
    this.launch {
        try {
            onSuccess(deferred.await())
        } catch (t: Throwable) {
            onFailed(t.localizedMessage)
        }
    }
}

inline fun <reified T, U> CoroutineScope.doSomethingWithDeferredPair(deferredSource1: Pair<Deferred<T>, Deferred<U>>,
                                                                     crossinline onSuccess: (T, U) -> Unit,
                                                                     crossinline onFailed: (String) -> Unit
) {
    this.launch {
        try {
            onSuccess(deferredSource1.first.await(), deferredSource1.second.await())
        } catch (t: Throwable) {
            onFailed(t.localizedMessage)
        }
    }
}

/*
//testing out
suspend fun <T> CoroutineScope.doSomethingWithDeferred(deferred: Deferred<T>): T {
    this.launch {
        try {
            return@launch deferred.run {
                await()
            }

        } catch (t: Throwable) {
            logE(t.localizedMessage)
        }
    }
    return deferred.await()
}*/
