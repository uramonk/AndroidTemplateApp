package com.uramonk.androidtemplateapp.domain.interactor

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by kaz on 2016/12/23.
 */

abstract class UseCase<T> {
    private var executionScheduler: Scheduler = Schedulers.newThread()
    private var postScheduler: Scheduler = AndroidSchedulers.mainThread()
    private var disposable: Disposable? = null

    protected constructor() {

    }

    protected constructor(executionScheduler: Scheduler, postScheduler: Scheduler) {
        this.executionScheduler = executionScheduler
        this.postScheduler = postScheduler
    }

    fun executionScheduler(executionScheduler: Scheduler): UseCase<T> {
        this.executionScheduler = executionScheduler
        return this
    }

    fun postScheduler(postScheduler: Scheduler): UseCase<T> {
        this.postScheduler = postScheduler
        return this
    }

    fun execute(observer: DisposableObserver<T>): Disposable {
        val observable: Observable<T> = this.buildObservableUseCase()
                .subscribeOn(executionScheduler)
                .observeOn(postScheduler)

        return observable.subscribeWith(observer)
    }

    fun execute(onNext: Consumer<in T>): Disposable {
        val observable: Observable<T> = this.buildObservableUseCase()
                .subscribeOn(executionScheduler)
                .observeOn(postScheduler)

        return observable.subscribe(onNext)
    }

    fun execute(onNext: Consumer<in T>, onError: Consumer<in Throwable>): Disposable {
        val observable: Observable<T> = this.buildObservableUseCase()
                .subscribeOn(executionScheduler)
                .observeOn(postScheduler)

        return observable.subscribe(onNext, onError)
    }

    fun execute(onNext: Consumer<in T>, onError: Consumer<in Throwable>,
            onCompleted: Action): Disposable {
        val observable: Observable<T> = this.buildObservableUseCase()
                .subscribeOn(executionScheduler)
                .observeOn(postScheduler)

        return observable.subscribe(onNext, onError, onCompleted)
    }

    protected abstract fun buildObservableUseCase(): Observable<T>

}
