package com.uramonk.androidtemplateapp.domain.store

import com.uramonk.androidtemplateapp.domain.model.WeatherList

import io.reactivex.Observable

/**
 * Created by uramonk on 2017/02/25.
 */

interface WeatherStore {

    fun update(weatherList: WeatherList)

    fun onUpdated(): Observable<WeatherList>

    fun getValue(): Observable<WeatherList>
}
