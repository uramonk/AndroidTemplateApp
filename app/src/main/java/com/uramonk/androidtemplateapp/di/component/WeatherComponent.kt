package com.uramonk.androidtemplateapp.di.component

import com.uramonk.androidtemplateapp.di.module.NetworkModule
import com.uramonk.androidtemplateapp.di.module.StoreModule
import com.uramonk.androidtemplateapp.di.module.UseCaseModule
import com.uramonk.androidtemplateapp.di.module.WeatherModule
import com.uramonk.androidtemplateapp.error.CommonErrorHandler
import com.uramonk.androidtemplateapp.presentation.viewmodel.MainFragmentViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by uramonk on 2016/06/29.
 */
@Singleton
@Component(modules = arrayOf(WeatherModule::class, NetworkModule::class, UseCaseModule::class, StoreModule::class))
interface WeatherComponent {
    fun inject(viewModel: MainFragmentViewModel)
    fun inject(commonErrorHandler: CommonErrorHandler)
}
