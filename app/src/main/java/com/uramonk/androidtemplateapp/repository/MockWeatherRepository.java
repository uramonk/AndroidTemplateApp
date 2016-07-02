package com.uramonk.androidtemplateapp.repository;

import com.uramonk.androidtemplateapp.entity.Weather;
import com.uramonk.androidtemplateapp.entity.WeatherEntity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by uramonk on 2016/06/29.
 */
public class MockWeatherRepository implements IWeatherRepository {
    public Observable<WeatherEntity> getWeather() {
        List<Weather> list = new ArrayList<>();
        Weather weather = new Weather(803, "Clouds", "broken clouds", "04d");
        list.add(weather);
        WeatherEntity weatherEntity = new WeatherEntity("stations", list);

        return Observable.just(weatherEntity)
                .subscribeOn(Schedulers.newThread());
    }
}
