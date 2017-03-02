package com.uramonk.androidtemplateapp.domain.interactor

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * Created by uramonk on 2017/02/25.
 */

class ClickButtonUseCase(private val buttonClickedObservable: Observable<Any>,
        private val interval: Long) : UseCase<Any>(AndroidSchedulers.mainThread(),
        AndroidSchedulers.mainThread()) {

    override fun buildObservableUseCase(): Observable<Any> {
        return buttonClickedObservable.throttleFirst(interval, TimeUnit.MILLISECONDS)
    }
}
