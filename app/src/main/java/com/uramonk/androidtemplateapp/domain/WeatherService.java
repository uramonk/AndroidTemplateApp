package com.uramonk.androidtemplateapp.domain;

import com.uramonk.androidtemplateapp.api.WeatherApi;
import com.uramonk.androidtemplateapp.model.WeatherEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.schedulers.Schedulers;


/**
 * Created by uramonk on 2016/06/18.
 */
@Deprecated
public class WeatherService extends BaseWeatherService {
    private final String API_KEY = "Your OpenWeatherMap API Key.";

    private WeatherApi weatherApi;

    public WeatherService() {
        super();
        weatherApi = retrofit.create(WeatherApi.class);
    }

    public Observable<WeatherEntity> getWeather() {
        return weatherApi.getWeather("Tokyo", API_KEY).subscribeOn(Schedulers.newThread());
    }
}
