package com.uramonk.androidtemplateapp.domain.store

import com.uramonk.androidtemplateapp.domain.model.WeatherList
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by uramonk on 2017/02/25.
 */

class WeatherDataStore : WeatherStore {

    private val behaviorSubject = BehaviorSubject.create<WeatherList>()

    override fun update(weatherList: WeatherList) {
        behaviorSubject.onNext(weatherList)
    }

    override fun onUpdated(): Observable<WeatherList> {
        return behaviorSubject.hide().share()
    }
}
