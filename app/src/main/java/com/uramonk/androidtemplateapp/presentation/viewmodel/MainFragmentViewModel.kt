package com.uramonk.androidtemplateapp.presentation.viewmodel

import android.databinding.ObservableField
import com.uramonk.androidtemplateapp.ModuleInjector
import com.uramonk.androidtemplateapp.R
import com.uramonk.androidtemplateapp.domain.interactor.ClickButtonUseCase
import com.uramonk.androidtemplateapp.domain.interactor.GetWeatherListUseCase
import com.uramonk.androidtemplateapp.domain.interactor.NotifyWeatherUseCase
import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.error.CommonErrorHandler
import com.uramonk.androidtemplateapp.presentation.model.WeatherListModel
import com.uramonk.androidtemplateapp.presentation.model.mapper.WeatherListModelDataMapper
import com.uramonk.androidtemplateapp.presentation.view.LicenseFragment
import com.uramonk.androidtemplateapp.presentation.view.MainFragment
import com.uramonk.androidtemplateapp.presentation.view.NextFragment
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.FunctionSubscriber
import rx.subscriptions.CompositeSubscription
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by uramonk on 2016/06/23.
 */
class MainFragmentViewModel(private val fragment: MainFragment) : BaseViewModel(fragment) {

    val text = ObservableField("")
    val weatherList = ObservableField<WeatherListModel>()

    var compositeSubscription: CompositeSubscription = CompositeSubscription()

    @Inject
    lateinit var getWeatherUseCase: GetWeatherListUseCase
    @Inject
    lateinit var notifyWeatherUseCase: NotifyWeatherUseCase

    lateinit var buttonClickedUseCase: ClickButtonUseCase
    lateinit var nextButtonClickedUseCase: ClickButtonUseCase
    lateinit var licenseButtonClickedUseCase: ClickButtonUseCase

    override fun onStart() {
        super.onStart()
        Timber.d("onStart")
        ModuleInjector.WeatherComponentInstance.get().inject(this)
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")

        createUseCase()
        executeUseCase()
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause")

        compositeSubscription.unsubscribe()
    }

    private fun createUseCase() {
        buttonClickedUseCase = ClickButtonUseCase(AndroidSchedulers.mainThread(),
                AndroidSchedulers.mainThread(),
                fragment.onButtonClicked(),
                0)
        nextButtonClickedUseCase = ClickButtonUseCase(AndroidSchedulers.mainThread(),
                AndroidSchedulers.mainThread(),
                fragment.onNextButtonClicked(),
                1000)
        licenseButtonClickedUseCase = ClickButtonUseCase(AndroidSchedulers.mainThread(),
                AndroidSchedulers.mainThread(),
                fragment.onLicenseButtonClicked(),
                1000)
    }

    private fun executeUseCase() {
        if (compositeSubscription.isUnsubscribed) {
            compositeSubscription = CompositeSubscription()
        }

        // Get and store WeatherList.
        compositeSubscription.add(getWeatherUseCase.execute(FunctionSubscriber<WeatherList>()
                .onError { weatherUseCaseError(it) }
        ))
        // Set WeatherList to View when store is updated.
        compositeSubscription.add(notifyWeatherUseCase.execute(FunctionSubscriber<WeatherList>()
                .onNext { setWeatherList(it) }
        ))
        // Set text when button clicked.
        compositeSubscription.add(buttonClickedUseCase.execute(FunctionSubscriber<Boolean>()
                .onNext { setText() }
        ))
        // Transition NextFragment when next button clicked.
        compositeSubscription.add(nextButtonClickedUseCase.execute(FunctionSubscriber<Boolean>()
                .onNext {
                    commitFragment(fragment.activity, NextFragment.newInstance(), R.id.container)
                }
        ))
        // Transition LicenseFragment when license button clicked.
        compositeSubscription.add(licenseButtonClickedUseCase.execute(FunctionSubscriber<Boolean>()
                .onNext {
                    commitFragment(fragment.activity, LicenseFragment.newInstance(), R.id.container)
                }
        ))
    }

    private fun setWeatherList(it: WeatherList) {
        val weatherListModel: WeatherListModel = WeatherListModelDataMapper().transform(it)
        weatherList.set(weatherListModel)
    }

    private fun weatherUseCaseError(it: Throwable) {
        Timber.e(it, "Error: WeatherService.getWeatherList")
        CommonErrorHandler().handleError(fragment, it)
    }

    private fun setText() {
        if (text.get().isEmpty()) {
            text.set("Button Clicked!")
        } else {
            text.set("")
        }
    }
}
