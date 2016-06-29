package com.uramonk.androidtemplateapp.entity;

import java.util.List;

/**
 * Created by uramonk on 2016/06/18.
 */
public class WeatherEntity {
    public final String base;
    public final List<Weather> weather;

    public WeatherEntity(String base, List<Weather> weather) {
        this.base = base;
        this.weather = weather;
    }
}
