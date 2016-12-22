package com.uramonk.androidtemplateapp.data.entity.mapper

import com.uramonk.androidtemplateapp.data.entity.WeatherEntity
import com.uramonk.androidtemplateapp.domain.model.Weather
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kaz on 2016/12/23.
 */
@Singleton
class WeatherEntityDataMapper
@Inject
constructor() {

    /**
     * transform data object into domain object
     */
    fun transform(weatherEntity: WeatherEntity): Weather {
        val weather: Weather = Weather(weatherEntity.id, weatherEntity.main, weatherEntity.description, weatherEntity.icon)
        return weather
    }

    fun transform(weatherEntityCollection: Collection<WeatherEntity>): List<Weather> {
        val weatherList = weatherEntityCollection.map { transform(it) }
        return weatherList
    }
}
