package com.uramonk.androidtemplateapp.domain

import com.uramonk.androidtemplateapp.domain.interactor.GetWeatherListUseCase
import com.uramonk.androidtemplateapp.domain.model.Weather
import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.domain.repository.WeatherRepository
import com.uramonk.androidtemplateapp.domain.store.WeatherStore
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.TestScheduler
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class GetWeatherListUseCaseTest {
    private lateinit var useCase: GetWeatherListUseCase
    private lateinit var testScheduler: TestScheduler
    private lateinit var weatherList: WeatherList
    private val weatherRepository: WeatherRepository = mock(WeatherRepository::class.java)
    private val weatherStore: WeatherStore = mock(WeatherStore::class.java)

    @Before fun setUp() {
        testScheduler = TestScheduler()
        useCase = GetWeatherListUseCase(weatherRepository, weatherStore)
        weatherList = WeatherList("base", ArrayList<Weather>(), 100L)
        `when`(weatherRepository.getWeatherList()).thenReturn(Observable.just(weatherList))
    }

    @Test fun testStoreUpdateAfterGetWeather() {
        useCase.executionScheduler(testScheduler).postScheduler(testScheduler).execute(
                onNext = Consumer {
                    assertThat(it).isEqualTo(weatherList)
                    verify(weatherRepository).getWeatherList()
                    verify(weatherStore).update(weatherList)
                })
        testScheduler.triggerActions()
    }
}
