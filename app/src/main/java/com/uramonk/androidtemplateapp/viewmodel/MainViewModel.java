package com.uramonk.androidtemplateapp.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.uramonk.androidtemplateapp.domain.WeatherService;
import com.uramonk.androidtemplateapp.model.WeatherEntity;

import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by uramonk on 2016/06/18.
 */
public class MainViewModel extends BaseViewModel {
    public final ObservableField<String> text = new ObservableField<>("");
    public final ObservableField<WeatherEntity> weatherEntity = new ObservableField<>();

    private RxAppCompatActivity activity;

    public MainViewModel(RxAppCompatActivity activity) {
        super(activity);
        this.activity = activity;
    }

    public void onClicked(View view) {
        if (text.get().isEmpty()) text.set("Button Clicked!");
        else text.set("");
    }

    @Override
    protected void onCreateView() {
        Timber.d("onCreateView");
    }

    @Override
    protected void onStartView() {
        Timber.d("onStartView");
    }

    @Override
    protected void onResumeView() {
        Timber.d("onResumeView");

        new WeatherService()
                .getWeather()
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.weatherEntity::set, throwable -> {
                    Timber.d(throwable, "Error: WeatherService.getWeather");
                });
    }

    @Override
    protected void onPauseView() {
        Timber.d("onPauseView");
    }

    @Override
    protected void onStopView() {
        Timber.d("onStopView");
    }

    @Override
    protected void onDestroyView() {
        Timber.d("onDestroyView");
    }

}
