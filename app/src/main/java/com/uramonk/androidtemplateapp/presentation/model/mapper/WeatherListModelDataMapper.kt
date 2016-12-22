package com.uramonk.androidtemplateapp.presentation.model.mapper

import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.presentation.model.WeatherListModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kaz on 2016/12/23.
 */
@Singleton
class WeatherListModelDataMapper
@Inject
constructor() {

    /**
     * transform domain object into presentation object
     */
    fun transform(weatherList: WeatherList): WeatherListModel {
        val weatherModelList: WeatherListModel = WeatherListModel(weatherList.base,
                WeatherModelDataMapper().transform(weatherList.weathers))
        return weatherModelList
    }

    fun transform(weatherListCollection: Collection<WeatherList>): List<WeatherListModel> {
        val weatherModelListList = weatherListCollection.map { transform(it) }
        return weatherModelListList
    }
}