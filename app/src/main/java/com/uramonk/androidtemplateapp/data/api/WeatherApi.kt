package com.uramonk.androidtemplateapp.data.api

import com.uramonk.androidtemplateapp.data.entity.WeatherListEntity

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by uramonk on 2016/06/29.
 */
interface WeatherApi {
    @GET("weather")
    fun getWeather(@Query("q") q: String, @Query("appid") appid: String): Observable<WeatherListEntity>
}
