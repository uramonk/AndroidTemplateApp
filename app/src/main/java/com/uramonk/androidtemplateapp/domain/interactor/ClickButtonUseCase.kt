package com.uramonk.androidtemplateapp.domain.interactor

import rx.Observable
import rx.Scheduler
import java.util.concurrent.TimeUnit

/**
 * Created by uramonk on 2017/02/25.
 */

class ClickButtonUseCase(executionScheduler: Scheduler,
                         postScheduler: Scheduler,
                         private val buttonClickedObservable: Observable<Void>,
                         private val interval: Long) : UseCase<Boolean>(executionScheduler, postScheduler) {

    override fun buildObservableUseCase(): Observable<Boolean> {
        return buttonClickedObservable.throttleFirst(interval, TimeUnit.MILLISECONDS).map { it -> true }
    }
}
