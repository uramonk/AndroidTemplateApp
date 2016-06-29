package com.uramonk.androidtemplateapp.model;

import com.uramonk.androidtemplateapp.api.WeatherApi;
import com.uramonk.androidtemplateapp.entity.WeatherEntity;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by uramonk on 2016/06/29.
 */
public class WeatherModel {
    private final String API_KEY = "Your OpenWeatherMap API Key";
    private WeatherApi weatherApi;

    protected WeatherModel() {

    }

    public WeatherModel(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public Observable<WeatherEntity> getWeather() {
        return weatherApi.getWeather("TOKYO", API_KEY)
                .subscribeOn(Schedulers.newThread());
    }
}
