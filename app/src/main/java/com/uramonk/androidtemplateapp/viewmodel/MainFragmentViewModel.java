package com.uramonk.androidtemplateapp.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.components.RxFragment;
import com.uramonk.androidtemplateapp.ModuleInjector;
import com.uramonk.androidtemplateapp.R;
import com.uramonk.androidtemplateapp.entity.WeatherEntity;
import com.uramonk.androidtemplateapp.error.CommonErrorHandler;
import com.uramonk.androidtemplateapp.repository.IWeatherRepository;
import com.uramonk.androidtemplateapp.view.MainFragment;
import com.uramonk.androidtemplateapp.view.NextFragment;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by uramonk on 2016/06/23.
 */
public class MainFragmentViewModel extends BaseViewModel {
    public final ObservableField<String> text = new ObservableField<>("");
    public final ObservableField<WeatherEntity> weatherEntity = new ObservableField<>();

    private MainFragment fragment;

    @Inject
    IWeatherRepository weatherRepository;

    public MainFragmentViewModel(MainFragment fragment) {
        super(fragment);
        this.fragment = fragment;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        Timber.d("onCreate");
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        Timber.d("onCreateView");
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        Timber.d("onAttach");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
        ModuleInjector.getInstance().getWeatherComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("onResume");

        subscribeSignals();
    }

    private void subscribeSignals() {
        weatherRepository.getWeather()
                .compose(fragment.bindUntilEvent(FragmentEvent.PAUSE))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.weatherEntity::set, throwable -> {
                    Timber.d(throwable, "Error: WeatherService.getWeather");
                    new CommonErrorHandler().handleError(fragment, throwable);
                });

        fragment.onButtonClicked()
                .compose(fragment.bindUntilEvent(FragmentEvent.PAUSE))
                .subscribe(aVoid -> {
                    if (text.get().isEmpty()) {
                        text.set("Button Clicked!");
                    } else {
                        text.set("");
                    }
                });

        fragment.onNextButtonClicked()
                .compose(fragment.bindUntilEvent(FragmentEvent.PAUSE))
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> commitFragment(this.fragment.getActivity(), NextFragment.newInstance(), R.id.container));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.d("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.d("onStop");
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        Timber.d("onDetach");
    }

    @Override
    protected void onDestroyView() {
        super.onDestroyView();
        Timber.d("onDestroyView");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
    }
}
