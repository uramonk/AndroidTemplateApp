package com.uramonk.androidtemplateapp.data.entity.mapper

import com.uramonk.androidtemplateapp.data.entity.WeatherListEntity
import com.uramonk.androidtemplateapp.domain.model.WeatherList
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kaz on 2016/12/23.
 */
@Singleton
class WeatherListEntityDataMapper
@Inject
constructor() {

    /**
     * transform data object into domain object
     */
    fun transform(weatherListEntity: WeatherListEntity): WeatherList {
        val weatherList: WeatherList = WeatherList(weatherListEntity.base,
                WeatherEntityDataMapper().transform(weatherListEntity.weather))
        return weatherList
    }

    fun transform(weatherListEntityCollection: Collection<WeatherListEntity>): List<WeatherList> {
        val weatherListList = weatherListEntityCollection.map { transform(it) }
        return weatherListList
    }
}
