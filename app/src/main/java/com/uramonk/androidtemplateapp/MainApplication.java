package com.uramonk.androidtemplateapp;

import android.app.Application;

import com.google.firebase.analytics.FirebaseAnalytics;

import timber.log.Timber;

/**
 * Created by uramonk on 2016/06/18.
 */
public class MainApplication extends Application {
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

}
