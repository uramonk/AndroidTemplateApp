package com.uramonk.androidtemplateapp.di.module

import com.uramonk.androidtemplateapp.domain.interactor.GetWeatherListUseCase
import com.uramonk.androidtemplateapp.domain.interactor.NotifyWeatherUseCase
import com.uramonk.androidtemplateapp.domain.repository.WeatherRepository
import com.uramonk.androidtemplateapp.domain.store.WeatherStore
import dagger.Module
import dagger.Provides
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by uramonk on 2017/02/25.
 */
@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetWeatherListUseCase(@Named("executionScheduler") executionScheduler: Scheduler,
                                     @Named("postScheduler") postScheduler: Scheduler,
                                     weatherRepository: WeatherRepository,
                                     weatherStore: WeatherStore): GetWeatherListUseCase {
        return GetWeatherListUseCase(executionScheduler, postScheduler, weatherRepository, weatherStore)
    }

    @Provides
    @Singleton
    fun provideBindWeatherUseCase(@Named("executionScheduler") executionScheduler: Scheduler,
                                  @Named("postScheduler") postScheduler: Scheduler,
                                  weatherStore: WeatherStore): NotifyWeatherUseCase {
        return NotifyWeatherUseCase(executionScheduler, postScheduler, weatherStore)
    }

    @Named("executionScheduler")
    @Provides
    @Singleton
    fun provideExecutionScheduler(): Scheduler {
        return Schedulers.newThread()
    }

    @Named("postScheduler")
    @Provides
    @Singleton
    fun providePostScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}