package com.ian.app.drinkings.helper

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
inline fun <reified T> CompositeDisposable.executes(
    obs: Observable<T>,
    crossinline onFailed: (Throwable) -> Unit,
    crossinline onSuccess: (T?) -> Unit
) {
    this.add(obs.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
        onSuccess(it)
    }, {
        onFailed(it)
    }))
}

fun CompositeDisposable.asyncRxExecutor(heavyFunction: () -> Unit?) {
    this.add(Observable.fromCallable(Runnable {
        heavyFunction()
    }::run).subscribeOn(Schedulers.io()).subscribe({
        logD(Constant.succesWork)
    }, {
        logE(Constant.failedWork)
    }))

}