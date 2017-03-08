package com.uramonk.androidtemplateapp.data.repository

import android.util.Pair
import com.uramonk.androidtemplateapp.Constants
import com.uramonk.androidtemplateapp.data.api.WeatherApi
import com.uramonk.androidtemplateapp.data.entity.mapper.WeatherListEntityDataMapper
import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.domain.repository.WeatherRepository

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import java.util.concurrent.TimeUnit

/**
 * Created by uramonk on 2016/06/29.
 */
class WeatherDataRepository(private val weatherApi: WeatherApi,
        private val weatherListEntityDataMapper: WeatherListEntityDataMapper) : WeatherRepository {

    companion object {
        val RETRY_NUM: Int = 2
        val RETRY_INTERVAL: Long = 2
    }

    override fun getWeatherList(): Observable<WeatherList> {
        return weatherApi.getWeather("TOKYO", Constants.OPEN_WEATHER_MAP_API_KEY).map {
            weatherListEntityDataMapper.transform(it)
        }.retryWhen(retryWhenError())
    }

    private fun retryWhenError(): Function<Observable<out Throwable>, Observable<Any>> {
        return Function { observable ->
            observable.zipWith(Observable.range(0, RETRY_NUM + 1),
                    BiFunction<Throwable, Int, Pair<Throwable, Int>> { throwable, integer ->
                        Pair(throwable, integer)
                    })
                    .flatMap<Any> { throwableIntegerPair ->
                        if (throwableIntegerPair.second < RETRY_NUM) {
                            Observable.timer(RETRY_INTERVAL, TimeUnit.SECONDS)
                        } else {
                            Observable.error(throwableIntegerPair.first)
                        }
                    }
        }
    }
}
