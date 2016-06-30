package com.uramonk.androidtemplateapp.module;

import com.uramonk.androidtemplateapp.api.WeatherApi;
import com.uramonk.androidtemplateapp.model.IWeatherRepository;
import com.uramonk.androidtemplateapp.model.MockWeatherRepository;
import com.uramonk.androidtemplateapp.model.WeatherRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by uramonk on 2016/06/29.
 */
@Module
public class WeatherModule {
    @Provides
    @Singleton
    public WeatherApi provideWeatherApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }

    @Provides
    @Singleton
    public IWeatherRepository provideWeatherModel(WeatherApi weatherApi) {
        return new WeatherRepository(weatherApi);
        //return new MockWeatherRepository();
    }
}
