package com.uramonk.androidtemplateapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.uramonk.androidtemplateapp.databinding.ActivityMainBinding;
import com.uramonk.androidtemplateapp.domain.WeatherService;

import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class MainActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        new WeatherService()
                .getWeather()
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(binding::setWeather, throwable -> {
                    Timber.d(throwable, "Error: WeatherService.getWeather");
                });
    }
}
