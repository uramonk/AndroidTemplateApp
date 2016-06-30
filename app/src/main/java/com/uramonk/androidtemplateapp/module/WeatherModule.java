package com.uramonk.androidtemplateapp.module;

import com.uramonk.androidtemplateapp.Constants;
import com.uramonk.androidtemplateapp.api.WeatherApi;
import com.uramonk.androidtemplateapp.repository.IWeatherRepository;
import com.uramonk.androidtemplateapp.repository.MockWeatherRepository;
import com.uramonk.androidtemplateapp.repository.WeatherRepository;

import javax.inject.Named;
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
    public IWeatherRepository provideWeatherRepository(WeatherApi weatherApi) {
        return new WeatherRepository(weatherApi);
    }

    @Provides @Named(Constants.MOCK)
    @Singleton
    public IWeatherRepository provideMockWeatherRepository() {
        return new MockWeatherRepository();
    }
}
