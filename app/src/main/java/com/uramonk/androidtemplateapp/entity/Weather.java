package com.uramonk.androidtemplateapp.entity;

/**
 * Created by uramonk on 2016/06/29.
 */
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
