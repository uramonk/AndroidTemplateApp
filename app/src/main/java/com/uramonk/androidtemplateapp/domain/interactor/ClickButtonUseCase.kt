package com.uramonk.androidtemplateapp.domain.interactor

import io.reactivex.Observable
import io.reactivex.Scheduler
import java.util.concurrent.TimeUnit

/**
 * Created by uramonk on 2017/02/25.
 */

class ClickButtonUseCase(executionScheduler: Scheduler, postScheduler: Scheduler,
        private val buttonClickedObservable: Observable<Any>,
        private val interval: Long) : UseCase<Any>(executionScheduler, postScheduler) {

    override fun buildObservableUseCase(): Observable<Any> {
        return buttonClickedObservable.throttleFirst(interval, TimeUnit.MILLISECONDS)
    }
}
