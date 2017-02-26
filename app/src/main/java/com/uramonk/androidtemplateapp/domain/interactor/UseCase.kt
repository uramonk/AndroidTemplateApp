package com.uramonk.androidtemplateapp.domain.interactor

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver

/**
 * Created by kaz on 2016/12/23.
 */

abstract class UseCase<T>
protected constructor(private var executionScheduler: Scheduler,
        private var postScheduler: Scheduler) {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun execute(observer: DisposableObserver<T>): Disposable {
        val observable: Observable<T> = this.buildObservableUseCase()
                .subscribeOn(executionScheduler)
                .observeOn(postScheduler)

        compositeDisposable.add(observable.subscribeWith(observer))

        return compositeDisposable
    }

    fun execute(onNext: Consumer<in T>): Disposable {
        val observable: Observable<T> = this.buildObservableUseCase()
                .subscribeOn(executionScheduler)
                .observeOn(postScheduler)

        compositeDisposable.add(observable.subscribe(onNext))

        return compositeDisposable
    }

    fun execute(onNext: Consumer<in T>, onError: Consumer<in Throwable>): Disposable {
        val observable: Observable<T> = this.buildObservableUseCase()
                .subscribeOn(executionScheduler)
                .observeOn(postScheduler)

        compositeDisposable.add(observable.subscribe(onNext, onError))

        return compositeDisposable
    }

    fun execute(onNext: Consumer<in T>, onError: Consumer<in Throwable>,
            onCompleted: Action): Disposable {
        val observable: Observable<T> = this.buildObservableUseCase()
                .subscribeOn(executionScheduler)
                .observeOn(postScheduler)

        compositeDisposable.add(observable.subscribe(onNext, onError, onCompleted))

        return compositeDisposable
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    protected abstract fun buildObservableUseCase(): Observable<T>
}
