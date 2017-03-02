package com.uramonk.androidtemplateapp.di.module

import com.uramonk.androidtemplateapp.domain.interactor.GetWeatherListUseCase
import com.uramonk.androidtemplateapp.domain.interactor.NotifyWeatherUseCase
import com.uramonk.androidtemplateapp.domain.repository.WeatherRepository
import com.uramonk.androidtemplateapp.domain.store.WeatherStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by uramonk on 2017/02/25.
 */
@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetWeatherListUseCase(weatherRepository: WeatherRepository,
            weatherStore: WeatherStore): GetWeatherListUseCase {
        return GetWeatherListUseCase(weatherRepository,
                weatherStore)
    }

    @Provides
    @Singleton
    fun provideBindWeatherUseCase(weatherStore: WeatherStore): NotifyWeatherUseCase {
        return NotifyWeatherUseCase(weatherStore)
    }
}