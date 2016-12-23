package com.uramonk.androidtemplateapp.presentation.model.mapper

import com.uramonk.androidtemplateapp.domain.model.Weather
import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.presentation.model.WeatherListModel
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by kaz on 2016/12/23.
 */
class WeatherListModelDataMapperTest {
    @Before
    fun setUp() {

    }

    @Test
    fun WeatherListをWeatherListModelに変換できる() {
        val list: MutableList<Weather> = mutableListOf()
        for (i: Int in 0..9) {
            val weather: Weather = Weather(i, "main", "description", "icon")
            list.add(weather)
        }

        val weatherList: WeatherList = WeatherList("base", list)
        val weatherListModel: WeatherListModel = WeatherListModelDataMapper().transform(weatherList)
        Assert.assertThat(weatherListModel.base, `is`("base"))
        for (i: Int in 0..weatherListModel.weathers.size - 1) {
            Assert.assertThat(weatherListModel.weathers[i].id, `is`(i))
            Assert.assertThat(weatherListModel.weathers[i].main, `is`("main"))
            Assert.assertThat(weatherListModel.weathers[i].description, `is`("description"))
            Assert.assertThat(weatherListModel.weathers[i].icon, `is`("icon"))
        }
    }

    @Test
    fun WeatherListのListをWeatherListModelのListに変換できる() {
        val list: MutableList<WeatherList> = mutableListOf()
        for (i: Int in 0..9) {
            val wList: MutableList<Weather> = mutableListOf()
            for (j: Int in 0..9) {
                val weather: Weather = Weather(j, "main", "description", "icon")
                wList.add(weather)
            }
            val weatherList: WeatherList = WeatherList("base" + i.toString(), wList)
            list.add(weatherList)
        }

        val weatherListModelList: List<WeatherListModel> = WeatherListModelDataMapper().transform(list)
        for (i: Int in 0..9) {
            Assert.assertThat(weatherListModelList[i].base, `is`("base" + i.toString()))
            for (j: Int in 0..9) {
                Assert.assertThat(weatherListModelList[i].weathers[j].id, `is`(j))
                Assert.assertThat(weatherListModelList[i].weathers[j].main, `is`("main"))
                Assert.assertThat(weatherListModelList[i].weathers[j].description, `is`("description"))
                Assert.assertThat(weatherListModelList[i].weathers[j].icon, `is`("icon"))
            }
        }
    }
}