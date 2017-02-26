package com.uramonk.androidtemplateapp.domain.interactor

import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.domain.store.WeatherStore
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by uramonk on 2017/02/25.
 */

class NotifyWeatherUseCase
@Inject
constructor(executionScheduler: Scheduler,
        postScheduler: Scheduler,
        private val weatherStore: WeatherStore) : UseCase<WeatherList>(executionScheduler,
        postScheduler) {

    override fun buildObservableUseCase(): Observable<WeatherList> {
        return weatherStore.onUpdated()
    }
}
