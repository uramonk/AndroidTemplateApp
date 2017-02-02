package com.uramonk.androidtemplateapp.domain.interactor

import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions

/**
 * Created by kaz on 2016/12/23.
 */

abstract class UseCase<T> protected constructor() {

    private var subscription = Subscriptions.empty()

    fun execute(useCaseSubscriber: Subscriber<T>) {
        this.subscription = this.buildObservableUseCase()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(useCaseSubscriber)
    }

    fun unsubscribe() {
        if (!subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
    }

    protected abstract fun buildObservableUseCase(): Observable<T>
}
