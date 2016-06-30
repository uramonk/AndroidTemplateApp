package com.uramonk.androidtemplateapp;

import com.uramonk.androidtemplateapp.component.DaggerWeatherComponent;
import com.uramonk.androidtemplateapp.component.WeatherComponent;
import com.uramonk.androidtemplateapp.module.NetworkModule;
import com.uramonk.androidtemplateapp.module.WeatherModule;

/**
 * Created by uramonk on 2016/06/22.
 */
public class ModuleInjector {
    /**
     * Components
     */
    private WeatherComponent weatherComponent;

    public static ModuleInjector instance = new ModuleInjector();

    private ModuleInjector() {

    }

    public static ModuleInjector getInstance() {
        return instance;
    }

    public WeatherComponent getWeatherComponent() {
        if (weatherComponent == null) {
            weatherComponent = DaggerWeatherComponent.builder()
                    .networkModule(new NetworkModule(Constants.OPEN_WEATHER_MAP_URL))
                    .weatherModule(new WeatherModule())
                    .build();
        }
        return weatherComponent;
    }
}
