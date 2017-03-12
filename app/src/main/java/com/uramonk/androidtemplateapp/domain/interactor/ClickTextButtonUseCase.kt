package com.uramonk.androidtemplateapp.domain.interactor

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * Created by uramonk on 2017/03/12.
 */

class ClickTextButtonUseCase(private var text: String,
        private val buttonClickedObservable: Observable<Any>,
        private val interval: Long) : UseCase<String>(AndroidSchedulers.mainThread(),
        AndroidSchedulers.mainThread()) {

    override fun buildObservableUseCase(): Observable<String> {
        return buttonClickedObservable.throttleFirst(interval, TimeUnit.MILLISECONDS).map { _ ->
            if (text.isEmpty()) {
                text = "Button Clicked!"
            } else {
                text = ""
            }
            return@map text
        }
    }
}
