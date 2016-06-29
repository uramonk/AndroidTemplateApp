package com.uramonk.androidtemplateapp.module;

import com.uramonk.androidtemplateapp.model.MockWeatherModel;
import com.uramonk.androidtemplateapp.model.WeatherModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by uramonk on 2016/06/29.
 */
@Module
public class MockWeatherModule {
    @Provides
    @Singleton
    public WeatherModel provideWeatherModel() {
        return new MockWeatherModel();
    }
}
