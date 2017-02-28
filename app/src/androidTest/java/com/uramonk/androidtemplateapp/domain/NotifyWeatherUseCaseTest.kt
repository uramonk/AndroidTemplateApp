package com.uramonk.androidtemplateapp.domain

import com.uramonk.androidtemplateapp.domain.interactor.NotifyWeatherUseCase
import com.uramonk.androidtemplateapp.domain.model.Weather
import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.domain.store.WeatherStore
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.TestScheduler
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.util.*

/**
 * Created by uramonk on 2017/02/28.
 */
class NotifyWeatherUseCaseTest {
    private lateinit var useCase: NotifyWeatherUseCase
    private lateinit var testScheduler: TestScheduler
    private lateinit var weatherList: WeatherList
    private val weatherStore: WeatherStore = Mockito.mock(WeatherStore::class.java)

    @Before
    fun setUp() {
        testScheduler = TestScheduler()
        useCase = NotifyWeatherUseCase(testScheduler, testScheduler, weatherStore)
        weatherList = WeatherList("base", ArrayList<Weather>())
    }

    @Test
    fun testOnUpdatedNotifyStoreUpdated() {
        `when`(weatherStore.onUpdated()).thenReturn(Observable.just(weatherList))
        useCase.execute(onNext = Consumer {
            verify(weatherStore).onUpdated()
            assertThat(it).isEqualTo(weatherList)
        })

        testScheduler.triggerActions()
    }
}