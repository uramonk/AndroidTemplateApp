package com.uramonk.androidtemplateapp.domain.store

import com.uramonk.androidtemplateapp.domain.model.WeatherList

import rx.Observable

/**
 * Created by uramonk on 2017/02/25.
 */

interface WeatherStore {

    fun update(weatherList: WeatherList)

    fun onUpdated(): Observable<WeatherList>
}