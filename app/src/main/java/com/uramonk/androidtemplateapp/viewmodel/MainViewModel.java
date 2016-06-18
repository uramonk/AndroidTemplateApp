package com.uramonk.androidtemplateapp.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import com.uramonk.androidtemplateapp.domain.WeatherService;
import com.uramonk.androidtemplateapp.model.WeatherEntity;

import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by uramonk on 2016/06/18.
 */
public class MainViewModel {
    public final ObservableField<String> text = new ObservableField<>("");
    public final ObservableField<WeatherEntity> weatherEntity = new ObservableField<>();

    public MainViewModel() {
        new WeatherService()
                .getWeather()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.weatherEntity::set, throwable -> {
                    Timber.d(throwable, "Error: WeatherService.getWeather");
                });
    }

    public void onClicked(View view) {
        if (text.get().isEmpty()) text.set("Button Clicked!");
        else text.set("");
    }
}
