package com.uramonk.androidtemplateapp.domain.interactor

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
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

    fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    protected abstract fun buildObservableUseCase(): Observable<T>
}
