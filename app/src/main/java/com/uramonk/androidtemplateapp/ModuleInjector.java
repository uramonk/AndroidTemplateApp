package com.uramonk.androidtemplateapp;

import com.uramonk.androidtemplateapp.component.DaggerWeatherComponent;
import com.uramonk.androidtemplateapp.component.WeatherComponent;

/**
 * Created by uramonk on 2016/06/22.
 */
public class ModuleInjector {
    private WeatherComponent weatherComponent;

    public static ModuleInjector instance = new ModuleInjector();

    private ModuleInjector() {

    }

    public static ModuleInjector getInstance() {
        return instance;
    }

    public synchronized WeatherComponent getWeatherComponent() {
        if (weatherComponent == null) {
            weatherComponent = DaggerWeatherComponent.builder().build();
        }
        return weatherComponent;
    }
}
