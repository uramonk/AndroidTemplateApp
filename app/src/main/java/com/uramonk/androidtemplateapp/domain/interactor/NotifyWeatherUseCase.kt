package com.uramonk.androidtemplateapp.domain.interactor

import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.domain.store.WeatherStore
import rx.Observable
import javax.inject.Inject

/**
 * Created by uramonk on 2017/02/25.
 */

class NotifyWeatherUseCase
@Inject
constructor(private val weatherStore: WeatherStore) : UseCase<WeatherList>() {

    override fun buildObservableUseCase(): Observable<WeatherList> {
        return weatherStore.onUpdated()
    }
}
