package com.uramonk.androidtemplateapp.module

import com.uramonk.androidtemplateapp.api.WeatherApi
import com.uramonk.androidtemplateapp.repository.IWeatherRepository
import com.uramonk.androidtemplateapp.repository.WeatherRepository

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by uramonk on 2016/06/29.
 */
@Module
class WeatherModule {
    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi): IWeatherRepository {
        return WeatherRepository(weatherApi)
    }
}
