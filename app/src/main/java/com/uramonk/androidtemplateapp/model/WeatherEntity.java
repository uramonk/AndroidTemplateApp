package com.uramonk.androidtemplateapp.model;

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

    public class Weather {
        public final int id;
        public final String main;
        public final String description;
        public final String icon;

        public Weather(int id, String main, String description, String icon) {
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }
    }
}
