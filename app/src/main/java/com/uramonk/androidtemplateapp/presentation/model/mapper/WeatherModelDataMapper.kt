package com.uramonk.androidtemplateapp.presentation.model.mapper

import com.uramonk.androidtemplateapp.domain.model.Weather
import com.uramonk.androidtemplateapp.presentation.model.WeatherModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kaz on 2016/12/23.
 */
@Singleton
class WeatherModelDataMapper
@Inject
constructor() {
    /**
     * transform domain object into presentation object
     */
    fun transform(weather: Weather): WeatherModel {
        val weatherModel: WeatherModel = WeatherModel(weather.id, weather.main, weather.description, weather.icon)
        return weatherModel
    }

    fun transform(weatherCollection: Collection<Weather>): List<WeatherModel> {
        val weatherModelList = weatherCollection.map { transform(it) }
        return weatherModelList
    }
}