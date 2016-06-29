package com.uramonk.androidtemplateapp.component;

import com.uramonk.androidtemplateapp.module.WeatherModule;
import com.uramonk.androidtemplateapp.viewmodel.MainFragmentViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by uramonk on 2016/06/29.
 */
@Singleton
@Component(modules = {WeatherModule.class})
public interface WeatherComponent {
    void inject(MainFragmentViewModel viewModel);
}
