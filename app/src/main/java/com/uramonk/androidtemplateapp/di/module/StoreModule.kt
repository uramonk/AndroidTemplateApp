package com.uramonk.androidtemplateapp.di.module

import com.uramonk.androidtemplateapp.data.store.WeatherDataStore
import com.uramonk.androidtemplateapp.domain.store.WeatherStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by uramonk on 2017/02/25.
 */
@Module
class StoreModule {
    @Provides
    @Singleton
    fun provideWeatherStore(): WeatherStore {
        return WeatherDataStore()
    }
}