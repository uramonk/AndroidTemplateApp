package com.uramonk.androidtemplateapp.data.repository

import com.uramonk.androidtemplateapp.Constants
import com.uramonk.androidtemplateapp.data.api.WeatherApi
import com.uramonk.androidtemplateapp.data.entity.mapper.WeatherListEntityDataMapper
import com.uramonk.androidtemplateapp.data.error.ApiStatus
import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.domain.repository.WeatherRepository

import io.reactivex.Observable
import io.reactivex.functions.Function
import retrofit2.HttpException
import java.util.concurrent.TimeUnit

/**
 * Created by uramonk on 2016/06/29.
 */
class WeatherDataRepository(private val weatherApi: WeatherApi,
        private val weatherListEntityDataMapper: WeatherListEntityDataMapper) : WeatherRepository {

    override fun getWeatherList(): Observable<WeatherList> {
        return weatherApi.getWeather("TOKYO", Constants.OPEN_WEATHER_MAP_API_KEY).map {
            weatherListEntityDataMapper.transform(it)
        }.retryWhen(retryWhenUnAuthorized())
    }

    private fun retryWhenUnAuthorized(): Function<Observable<out Throwable>, Observable<*>> {
        return Function { observable ->
            observable.take(2).flatMap<HttpException> { it ->
                if (it is HttpException) {
                    val httpException: HttpException = it
                    Observable.just(httpException)
                } else {
                    Observable.error { it }
                }
            }.flatMap<Any> { it ->
                if (it.code() == ApiStatus.UNAUTHORIZED.value) {
                    Observable.timer(3000, TimeUnit.MILLISECONDS);
                } else {
                    Observable.error { it }
                }
            }
        }
    }
}
