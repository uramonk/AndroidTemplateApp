package com.uramonk.androidtemplateapp.data.entity.mapper

import com.uramonk.androidtemplateapp.data.entity.WeatherEntity
import com.uramonk.androidtemplateapp.domain.model.Weather
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by kaz on 2016/12/23.
 */
class WeatherEntityDataMapperTest {
    @Before
    fun setUp() {

    }

    @Test
    fun WeatherEntityをWeatherに変換できる() {
        val weatherEntity: WeatherEntity = WeatherEntity(1, "main", "description", "icon")
        val weather: Weather = WeatherEntityDataMapper().transform(weatherEntity)
        Assert.assertThat(weather.id, `is`(1))
        Assert.assertThat(weather.main, `is`("main"))
        Assert.assertThat(weather.description, `is`("description"))
        Assert.assertThat(weather.icon, `is`("icon"))
    }

    @Test
    fun WeatherEntityのListをWeatherのListに変換できる() {
        val list: MutableList<WeatherEntity> = mutableListOf()
        for (i: Int in 0..9) {
            val weatherEntity: WeatherEntity = WeatherEntity(i, "main", "description", "icon")
            list.add(weatherEntity)
        }

        val weathers: List<Weather> = WeatherEntityDataMapper().transform(list)
        for (i: Int in 0..weathers.size - 1) {
            Assert.assertThat(weathers[i].id, `is`(i))
            Assert.assertThat(weathers[i].main, `is`("main"))
            Assert.assertThat(weathers[i].description, `is`("description"))
            Assert.assertThat(weathers[i].icon, `is`("icon"))
        }
    }
}