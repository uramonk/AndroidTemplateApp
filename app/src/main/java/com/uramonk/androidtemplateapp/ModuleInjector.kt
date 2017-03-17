package com.uramonk.androidtemplateapp

import com.uramonk.androidtemplateapp.di.component.DaggerWeatherComponent
import com.uramonk.androidtemplateapp.di.component.WeatherComponent
import com.uramonk.androidtemplateapp.di.module.NetworkModule
import com.uramonk.androidtemplateapp.di.module.WeatherModule

/**
 * Created by uramonk on 2016/06/22.
 */

class ModuleInjector private constructor() {
    /**
     * Singleton Components
     */
    object SingletonWeatherComponent {
        val instance: WeatherComponent = DaggerWeatherComponent.builder()
                .networkModule(NetworkModule(Constants.OPEN_WEATHER_MAP_URL))
                .weatherModule(WeatherModule())
                .build()
    }
}
