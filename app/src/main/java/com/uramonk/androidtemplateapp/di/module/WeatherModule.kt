package com.uramonk.androidtemplateapp.di.module

import com.uramonk.androidtemplateapp.data.api.WeatherApi
import com.uramonk.androidtemplateapp.data.entity.mapper.WeatherListEntityDataMapper
import com.uramonk.androidtemplateapp.data.repository.WeatherDataRepository
import com.uramonk.androidtemplateapp.domain.interactor.UseCase
import com.uramonk.androidtemplateapp.domain.interactor.WeatherUseCase
import com.uramonk.androidtemplateapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

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
    fun provideWeatherListEntityDataMapper(): WeatherListEntityDataMapper {
        return WeatherListEntityDataMapper()
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi, weatherListEntityDataMapper: WeatherListEntityDataMapper): WeatherRepository {
        return WeatherDataRepository(weatherApi, weatherListEntityDataMapper)
    }

    @Provides
    @Singleton
    fun provideUseCase(weatherRepository: WeatherRepository): UseCase {
        return WeatherUseCase(weatherRepository)
    }
}
