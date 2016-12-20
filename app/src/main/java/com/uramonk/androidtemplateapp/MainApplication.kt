package com.uramonk.androidtemplateapp

import android.app.Application

import com.google.firebase.analytics.FirebaseAnalytics

import timber.log.Timber

/**
 * Created by uramonk on 2016/06/18.
 */
class MainApplication : Application() {
    private var firebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }

}
