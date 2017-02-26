package com.uramonk.androidtemplateapp.data.repository

import com.uramonk.androidtemplateapp.Constants
import com.uramonk.androidtemplateapp.data.api.WeatherApi
import com.uramonk.androidtemplateapp.data.entity.mapper.WeatherListEntityDataMapper
import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.domain.repository.WeatherRepository

import io.reactivex.Observable

/**
 * Created by uramonk on 2016/06/29.
 */
class WeatherDataRepository(private val weatherApi: WeatherApi,
        private val weatherListEntityDataMapper: WeatherListEntityDataMapper) : WeatherRepository {

    override fun getWeatherList(): Observable<WeatherList> {
        return weatherApi.getWeather("TOKYO", Constants.OPEN_WEATHER_MAP_API_KEY).map {
            weatherListEntityDataMapper.transform(it)
        }
    }
}
