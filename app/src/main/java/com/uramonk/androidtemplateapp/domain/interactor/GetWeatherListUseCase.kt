package com.uramonk.androidtemplateapp.domain.interactor

import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.domain.repository.WeatherRepository

import javax.inject.Inject

import rx.Observable

/**
 * Created by kaz on 2016/12/23.
 */

class GetWeatherListUseCase
@Inject
constructor(private val weatherRepository: WeatherRepository) : UseCase<WeatherList>() {

    override fun buildObservableUseCase(): Observable<WeatherList> {
        return this.weatherRepository.getWeatherList()
    }
}
