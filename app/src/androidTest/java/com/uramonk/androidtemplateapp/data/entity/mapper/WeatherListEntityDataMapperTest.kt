package com.uramonk.androidtemplateapp.data.entity.mapper

import com.uramonk.androidtemplateapp.data.entity.WeatherEntity
import com.uramonk.androidtemplateapp.data.entity.WeatherListEntity
import com.uramonk.androidtemplateapp.domain.model.WeatherList
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by kaz on 2016/12/23.
 */
class WeatherListEntityDataMapperTest {
    @Before
    fun setUp() {

    }

    @Test
    fun WeatherListEntityをWeatherListに変換できる() {
        val list: MutableList<WeatherEntity> = mutableListOf()
        for (i: Int in 0..9) {
            val weatherEntity: WeatherEntity = WeatherEntity(i, "main", "description", "icon")
            list.add(weatherEntity)
        }

        val weatherListEntity: WeatherListEntity = WeatherListEntity("base", list)
        val weatherList: WeatherList = WeatherListEntityDataMapper().transform(weatherListEntity)
        Assert.assertThat(weatherList.base, `is`("base"))
        for (i: Int in 0..weatherList.weathers.size - 1) {
            Assert.assertThat(weatherList.weathers[i].id, `is`(i))
            Assert.assertThat(weatherList.weathers[i].main, `is`("main"))
            Assert.assertThat(weatherList.weathers[i].description, `is`("description"))
            Assert.assertThat(weatherList.weathers[i].icon, `is`("icon"))
        }
    }

    @Test
    fun WeatherListEntityのListをWeatherListのListに変換できる() {
        val list: MutableList<WeatherListEntity> = mutableListOf()
        for (i: Int in 0..9) {
            val wList: MutableList<WeatherEntity> = mutableListOf()
            for (j: Int in 0..9) {
                val weatherEntity: WeatherEntity = WeatherEntity(j, "main", "description", "icon")
                wList.add(weatherEntity)
            }
            val weatherListEntity: WeatherListEntity = WeatherListEntity("base" + i.toString(), wList)
            list.add(weatherListEntity)
        }

        val weatherListList: List<WeatherList> = WeatherListEntityDataMapper().transform(list)
        for (i: Int in 0..9) {
            Assert.assertThat(weatherListList[i].base, `is`("base" + i.toString()))
            for (j: Int in 0..9) {
                Assert.assertThat(weatherListList[i].weathers[j].id, `is`(j))
                Assert.assertThat(weatherListList[i].weathers[j].main, `is`("main"))
                Assert.assertThat(weatherListList[i].weathers[j].description, `is`("description"))
                Assert.assertThat(weatherListList[i].weathers[j].icon, `is`("icon"))
            }
        }
    }
}