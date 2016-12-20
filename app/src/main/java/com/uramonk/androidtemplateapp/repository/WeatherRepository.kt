package com.uramonk.androidtemplateapp.repository

import com.uramonk.androidtemplateapp.Constants
import com.uramonk.androidtemplateapp.api.WeatherApi
import com.uramonk.androidtemplateapp.entity.WeatherEntity

import rx.Observable
import rx.schedulers.Schedulers

/**
 * Created by uramonk on 2016/06/29.
 */
class WeatherRepository(private val weatherApi: WeatherApi) : IWeatherRepository {
    override fun getWeather(): Observable<WeatherEntity> {
        return weatherApi.getWeather("TOKYO", Constants.OPEN_WEATHER_MAP_API_KEY)
                .subscribeOn(Schedulers.newThread())
    }
}
