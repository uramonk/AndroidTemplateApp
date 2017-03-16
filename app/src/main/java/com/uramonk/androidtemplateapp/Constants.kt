package com.uramonk.androidtemplateapp

/**
 * Created by uramonk on 2016/06/29.
 */
object Constants {
    /**
     * OpenWeatherMap
     */
    val OPEN_WEATHER_MAP_URL = "http://api.openweathermap.org/data/2.5/"

    /**
     * Must define `weatherMapApiKey` in local.properties
     */
    val OPEN_WEATHER_MAP_API_KEY = BuildConfig.WEATHER_MAP_API_KEY

    val CACHE_INTERVAL = 60000
}
