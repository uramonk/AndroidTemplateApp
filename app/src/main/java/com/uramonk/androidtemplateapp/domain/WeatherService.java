package com.uramonk.androidtemplateapp.domain;

import com.uramonk.androidtemplateapp.model.WeatherEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.schedulers.Schedulers;


/**
 * Created by uramonk on 2016/06/18.
 */
public class WeatherService extends BaseWeatherService {
    private WeatherApi weatherApi;

    public interface WeatherApi {
        @GET("weather")
        Observable<WeatherEntity> getWeather(@Query("q") String q, @Query("appid") String appid);
    }

    public WeatherService() {
        super();
        weatherApi = retrofit.create(WeatherApi.class);
    }

    public Observable<WeatherEntity> getWeather() {
        return weatherApi.getWeather("Tokyo", API_KEY).subscribeOn(Schedulers.newThread());
    }
}
