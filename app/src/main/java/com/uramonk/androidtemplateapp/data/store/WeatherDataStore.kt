package com.uramonk.androidtemplateapp.data.store

import com.uramonk.androidtemplateapp.domain.model.Weather
import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.domain.store.WeatherStore
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*

/**
 * Created by uramonk on 2017/02/25.
 */

class WeatherDataStore : WeatherStore {

    private val behaviorSubject = BehaviorSubject.createDefault(
            WeatherList("", ArrayList<Weather>(), 0L))

    override fun update(weatherList: WeatherList) {
        behaviorSubject.onNext(weatherList)
    }

    override fun onUpdated(): Observable<WeatherList> {
        return behaviorSubject.hide().share()
    }

    override fun getValue(): Observable<WeatherList> {
        return Observable.just(behaviorSubject.getValue())
    }
}
