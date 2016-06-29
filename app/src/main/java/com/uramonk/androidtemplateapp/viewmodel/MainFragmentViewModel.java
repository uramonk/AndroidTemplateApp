package com.uramonk.androidtemplateapp.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.components.RxFragment;
import com.uramonk.androidtemplateapp.ModuleInjector;
import com.uramonk.androidtemplateapp.entity.WeatherEntity;
import com.uramonk.androidtemplateapp.error.CommonErrorHandler;
import com.uramonk.androidtemplateapp.model.WeatherModel;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by uramonk on 2016/06/23.
 */
public class MainFragmentViewModel extends BaseViewModel {
    public final ObservableField<String> text = new ObservableField<>("");
    public final ObservableField<WeatherEntity> weatherEntity = new ObservableField<>();

    private RxFragment fragment;

    @Inject
    WeatherModel weatherModel;

    public MainFragmentViewModel(RxFragment fragment) {
        super(fragment);
        this.fragment = fragment;
    }

    @Override
    protected void onCreateView() {
        Timber.d("onCreateView");
        ModuleInjector.getInstance().getWeatherComponent().inject(this);
    }

    @Override
    protected void onStartView() {
        Timber.d("onStartView");
    }

    @Override
    protected void onResumeView() {
        Timber.d("onResumeView");

        weatherModel.getWeather()
                .compose(fragment.bindUntilEvent(FragmentEvent.PAUSE))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.weatherEntity::set, throwable -> {
                    Timber.d(throwable, "Error: WeatherService.getWeather");
                    new CommonErrorHandler().handleError(fragment, throwable);
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

    public void onClicked(View view) {
        if (text.get().isEmpty()) {
            text.set("Button Clicked!");
        } else {
            text.set("");
        }
    }
}
