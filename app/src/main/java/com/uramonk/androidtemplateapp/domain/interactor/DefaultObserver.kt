package com.uramonk.androidtemplateapp.domain.interactor

import io.reactivex.observers.DisposableObserver

/**
 * Created by uramonk on 2017/02/25.
 */

open class DefaultObserver<T> : DisposableObserver<T>() {
    fun DefaultObserver() {

    }

    override fun onNext(value: T) {

    }

    override fun onError(e: Throwable) {

    }

    override fun onComplete() {

    }
}
