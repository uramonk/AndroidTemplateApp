package com.uramonk.androidtemplateapp.repository

import android.support.test.runner.AndroidJUnit4

import com.uramonk.androidtemplateapp.api.WeatherApi
import com.uramonk.androidtemplateapp.entity.WeatherEntity
import com.uramonk.androidtemplateapp.module.WeatherModule
import com.uramonk.androidtemplateapp.utility.TestUtility

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

import java.io.IOException

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat

/**
 * Created by uramonk on 2016/07/02.
 */
@RunWith(AndroidJUnit4::class)
class WeatherRepositoryTest {
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    private fun getWeatherRepository(mockWebServer: MockWebServer): IWeatherRepository {
        val retrofit = TestUtility.getRetrofitWithMockWebServer(mockWebServer)

        val weatherModule = WeatherModule()
        val weatherApi = weatherModule.provideWeatherApi(retrofit)
        return weatherModule.provideWeatherRepository(weatherApi)
    }

    @Test
    @Throws(IOException::class)
    fun Weatherを取得できる() {
        val mockWebServer = MockWebServer()
        val weatherRepository = getWeatherRepository(mockWebServer)
        mockWebServer.enqueue(MockResponse().setBody(TestUtility.createJsonString("weather_normal.json")))

        val weatherEntity = weatherRepository.getWeather().toBlocking().single()
        assertThat(weatherEntity.base, `is`("stations"))
        assertThat(weatherEntity.weather.size, `is`(1))
        assertThat(weatherEntity.weather[0].description, `is`("broken clouds"))
        assertThat(weatherEntity.weather[0].icon, `is`("04d"))
        assertThat(weatherEntity.weather[0].id, `is`(803))
        assertThat(weatherEntity.weather[0].main, `is`("Clouds"))

        mockWebServer.shutdown()
    }
}
