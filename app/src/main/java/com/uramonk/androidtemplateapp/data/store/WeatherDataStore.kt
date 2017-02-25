package com.uramonk.androidtemplateapp.data.store

import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.domain.store.WeatherStore

import rx.Observable
import rx.subjects.BehaviorSubject

/**
 * Created by uramonk on 2017/02/25.
 */

class WeatherDataStore : WeatherStore {

    private val behaviorSubject = BehaviorSubject.create<WeatherList>()

    override fun update(weatherList: WeatherList) {
        behaviorSubject.onNext(weatherList)
    }

    override fun onUpdated(): Observable<WeatherList> {
        return behaviorSubject.asObservable().share()
    }
}
