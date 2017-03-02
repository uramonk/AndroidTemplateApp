package com.uramonk.androidtemplateapp.domain.interactor

import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.domain.repository.WeatherRepository
import com.uramonk.androidtemplateapp.domain.store.WeatherStore
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by kaz on 2016/12/23.
 */

class GetWeatherListUseCase
@Inject
constructor(private val weatherRepository: WeatherRepository,
        private val weatherStore: WeatherStore) : UseCase<WeatherList>() {

    override fun buildObservableUseCase(): Observable<WeatherList> {
        return this.weatherRepository.getWeatherList()
                .doOnNext { weatherStore.update(it) }
    }
}
