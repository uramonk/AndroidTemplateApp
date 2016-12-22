package com.uramonk.androidtemplateapp.domain.repository

import com.uramonk.androidtemplateapp.domain.model.WeatherList

import rx.Observable

/**
 * Created by uramonk on 2016/06/30.
 */
interface WeatherRepository {
    fun getWeatherList(): Observable<WeatherList>
}
