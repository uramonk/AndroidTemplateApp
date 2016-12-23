package com.uramonk.androidtemplateapp.presentation.model.mapper

import com.uramonk.androidtemplateapp.domain.model.Weather
import com.uramonk.androidtemplateapp.presentation.model.WeatherModel
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by kaz on 2016/12/23.
 */
class WeatherModelDataMapperTest {
    @Before
    fun setUp() {

    }

    @Test
    fun WeatherをWeatherModelに変換できる() {
        val weather: Weather = Weather(1, "main", "description", "icon")
        val weatherModel: WeatherModel = WeatherModelDataMapper().transform(weather)
        Assert.assertThat(weatherModel.id, `is`(1))
        Assert.assertThat(weatherModel.main, `is`("main"))
        Assert.assertThat(weatherModel.description, `is`("description"))
        Assert.assertThat(weatherModel.icon, `is`("icon"))
    }

    @Test
    fun WeatherのListをWeatherMpdelのListに変換できる() {
        val list: MutableList<Weather> = mutableListOf()
        for (i: Int in 0..9) {
            val weather: Weather = Weather(i, "main", "description", "icon")
            list.add(weather)
        }

        val weatherModels: List<WeatherModel> = WeatherModelDataMapper().transform(list)
        for (i: Int in 0..weatherModels.size - 1) {
            Assert.assertThat(weatherModels[i].id, `is`(i))
            Assert.assertThat(weatherModels[i].main, `is`("main"))
            Assert.assertThat(weatherModels[i].description, `is`("description"))
            Assert.assertThat(weatherModels[i].icon, `is`("icon"))
        }
    }
}