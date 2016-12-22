package com.uramonk.androidtemplateapp.domain.interactor

/**
 * Created by kaz on 2016/12/23.
 */

abstract class DefaultSubscriber<T> : rx.Subscriber<T>() {

    override fun onCompleted() {

    }

    override fun onError(throwable: Throwable) {

    }

    override fun onNext(t: T) {

    }
}