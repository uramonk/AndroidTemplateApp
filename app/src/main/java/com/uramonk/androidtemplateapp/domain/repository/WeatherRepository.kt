package com.uramonk.androidtemplateapp.domain.repository

import com.uramonk.androidtemplateapp.domain.model.WeatherList

import io.reactivex.Observable

/**
 * Created by uramonk on 2016/06/30.
 */
interface WeatherRepository {
    fun getWeatherList(): Observable<WeatherList>
}
