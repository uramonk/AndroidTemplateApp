package com.uramonk.androidtemplateapp.repository

import android.support.test.runner.AndroidJUnit4
import com.uramonk.androidtemplateapp.di.module.WeatherModule
import com.uramonk.androidtemplateapp.domain.repository.WeatherRepository
import com.uramonk.androidtemplateapp.utility.TestUtility
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import java.io.IOException

/**
 * Created by uramonk on 2016/07/02.
 */
@RunWith(AndroidJUnit4::class)
class WeatherDataRepositoryTest {
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    private fun getWeatherRepository(mockWebServer: MockWebServer): WeatherRepository {
        val retrofit = TestUtility.getRetrofitWithMockWebServer(mockWebServer)

        val weatherModule = WeatherModule()
        val weatherApi = weatherModule.provideWeatherApi(retrofit)
        val weatherListEntityDataMapper = weatherModule.provideWeatherListEntityDataMapper()
        return weatherModule.provideWeatherRepository(weatherApi, weatherListEntityDataMapper)
    }

    @Test
    @Throws(IOException::class)
    fun Weatherを取得できる() {
        val mockWebServer = MockWebServer()
        val weatherRepository = getWeatherRepository(mockWebServer)
        mockWebServer.enqueue(MockResponse().setBody(TestUtility.createJsonString("weather_normal.json")))

        val weatherEntity = weatherRepository.getWeatherList().toBlocking().single()
        assertThat(weatherEntity.base, `is`("stations"))
        assertThat(weatherEntity.weathers.size, `is`(1))
        assertThat(weatherEntity.weathers[0].description, `is`("broken clouds"))
        assertThat(weatherEntity.weathers[0].icon, `is`("04d"))
        assertThat(weatherEntity.weathers[0].id, `is`(803))
        assertThat(weatherEntity.weathers[0].main, `is`("Clouds"))

        mockWebServer.shutdown()
    }
}
