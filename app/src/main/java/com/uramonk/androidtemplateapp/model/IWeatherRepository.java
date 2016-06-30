package com.uramonk.androidtemplateapp.model;

import com.uramonk.androidtemplateapp.entity.WeatherEntity;

import rx.Observable;

/**
 * Created by uramonk on 2016/06/30.
 */
public interface IWeatherRepository {
    Observable<WeatherEntity> getWeather();
}
