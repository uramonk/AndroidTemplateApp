package com.uramonk.androidtemplateapp.domain.interactor

import com.uramonk.androidtemplateapp.domain.repository.WeatherRepository

import javax.inject.Inject

import rx.Observable

/**
 * Created by kaz on 2016/12/23.
 */

class WeatherUseCase
@Inject
constructor(private val weatherRepository: WeatherRepository) : UseCase() {

    override fun buildObservableUseCase(): Observable<*> {
        return this.weatherRepository.getWeatherList()
    }
}
