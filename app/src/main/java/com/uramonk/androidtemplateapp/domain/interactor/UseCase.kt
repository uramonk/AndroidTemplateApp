package com.uramonk.androidtemplateapp.domain.interactor

import rx.Observable
import rx.Scheduler
import rx.Subscriber
import rx.Subscription
import rx.subscriptions.Subscriptions

/**
 * Created by kaz on 2016/12/23.
 */

abstract class UseCase<T>
protected constructor(private var executionScheduler: Scheduler,
                      private var postScheduler: Scheduler) {

    private var subscription = Subscriptions.empty()

    fun execute(useCaseSubscriber: Subscriber<T>): Subscription {
        this.subscription = this.buildObservableUseCase()
                .subscribeOn(executionScheduler)
                .observeOn(postScheduler)
                .subscribe(useCaseSubscriber)
        return this.subscription
    }

    fun unsubscribe() {
        if (!subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
    }

    protected abstract fun buildObservableUseCase(): Observable<T>
}
