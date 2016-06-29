package com.uramonk.androidtemplateapp.api;

import com.uramonk.androidtemplateapp.model.WeatherEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by uramonk on 2016/06/29.
 */
public interface WeatherApi {
    @GET("weather")
    Observable<WeatherEntity> getWeather(@Query("q") String q, @Query("appid") String appid);
}
