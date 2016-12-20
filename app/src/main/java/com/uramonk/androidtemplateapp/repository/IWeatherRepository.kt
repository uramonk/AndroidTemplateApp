package com.uramonk.androidtemplateapp.repository

import com.uramonk.androidtemplateapp.entity.WeatherEntity

import rx.Observable

/**
 * Created by uramonk on 2016/06/30.
 */
interface IWeatherRepository {
    fun getWeather(): Observable<WeatherEntity>
}
