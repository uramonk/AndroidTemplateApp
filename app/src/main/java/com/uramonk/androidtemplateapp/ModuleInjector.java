package com.uramonk.androidtemplateapp;

import com.uramonk.androidtemplateapp.domain.WeatherService;

/**
 * Created by uramonk on 2016/06/22.
 */
public class ModuleInjector {
    private WeatherService weatherService;

    public static ModuleInjector instance = new ModuleInjector();

    private ModuleInjector() {

    }

    public static ModuleInjector getInstance() {
        return instance;
    }

    public synchronized WeatherService getWeatherService() {
        if (weatherService == null) {
            weatherService = new WeatherService();
        }
        return weatherService;
    }
}
