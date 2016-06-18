package com.uramonk.androidtemplateapp;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by uramonk on 2016/06/18.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}
