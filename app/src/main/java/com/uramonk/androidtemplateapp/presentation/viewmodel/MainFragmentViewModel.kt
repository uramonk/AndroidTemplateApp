package com.uramonk.androidtemplateapp.presentation.viewmodel

import android.app.ProgressDialog
import android.content.Context
import android.databinding.ObservableField
import android.support.v7.app.AlertDialog
import com.uramonk.androidtemplateapp.ModuleInjector
import com.uramonk.androidtemplateapp.R
import com.uramonk.androidtemplateapp.data.error.ApiError
import com.uramonk.androidtemplateapp.domain.interactor.ClickButtonUseCase
import com.uramonk.androidtemplateapp.domain.interactor.ClickTextButtonUseCase
import com.uramonk.androidtemplateapp.domain.interactor.GetWeatherListUseCase
import com.uramonk.androidtemplateapp.domain.interactor.NotifyWeatherUseCase
import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.presentation.model.WeatherListModel
import com.uramonk.androidtemplateapp.presentation.model.mapper.WeatherListModelDataMapper
import com.uramonk.androidtemplateapp.presentation.view.LicenseFragment
import com.uramonk.androidtemplateapp.presentation.view.MainFragment
import com.uramonk.androidtemplateapp.presentation.view.NextFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by uramonk on 2016/06/23.
 */
class MainFragmentViewModel(private val fragment: MainFragment) : BaseViewModel(fragment) {

    val text = ObservableField("")
    val weatherList = ObservableField<WeatherListModel>()
    var progressDialog: ProgressDialog? = null
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var getWeatherUseCase: GetWeatherListUseCase
    @Inject
    lateinit var notifyWeatherUseCase: NotifyWeatherUseCase

    lateinit var buttonClickedUseCase: ClickTextButtonUseCase
    lateinit var nextButtonClickedUseCase: ClickButtonUseCase
    lateinit var licenseButtonClickedUseCase: ClickButtonUseCase
    lateinit var getWeatherButtonClickedUseCase: ClickButtonUseCase

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

        compositeDisposable.dispose()
    }

    private fun createUseCase() {
        buttonClickedUseCase = ClickTextButtonUseCase(text.get(), fragment.onButtonClicked(), 0)
        nextButtonClickedUseCase = ClickButtonUseCase(fragment.onNextButtonClicked(), 1000)
        licenseButtonClickedUseCase = ClickButtonUseCase(fragment.onLicenseButtonClicked(), 1000)
        getWeatherButtonClickedUseCase = ClickButtonUseCase(fragment.onGetWeatherButtonClicked(),
                1000)
    }

    private fun executeUseCase() {
        if (compositeDisposable.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }

        // Set WeatherList to View when store is updated.
        compositeDisposable.add(
                notifyWeatherUseCase.execute(
                        onNext = Consumer<WeatherList> {
                            weatherList.set(WeatherListModelDataMapper().transform(it))
                        }))
        // Set text when button clicked.
        compositeDisposable.add(
                buttonClickedUseCase.execute(
                        onNext = Consumer<String> { it ->
                            text.set(it)
                        }))
        // Transition NextFragment when next button clicked.
        compositeDisposable.add(
                nextButtonClickedUseCase.execute(
                        onNext = Consumer<Any> {
                            commitFragment(fragment.activity, NextFragment.newInstance(),
                                    R.id.container)
                        }))
        // Transition LicenseFragment when license button clicked.
        compositeDisposable.add(
                licenseButtonClickedUseCase.execute(
                        onNext = Consumer<Any> {
                            commitFragment(fragment.activity, LicenseFragment.newInstance(),
                                    R.id.container)
                        }))
        // Get WeatherList by user
        compositeDisposable.add(
                getWeatherButtonClickedUseCase.execute(
                        onNext = Consumer<Any> {
                            requestWeather()
                        }))

        // Get and store WeatherList.
        requestWeather()
    }

    private fun requestWeather() {
        showProgressDialog()
        compositeDisposable.add(getWeatherUseCase.execute(
                onNext = Consumer<WeatherList> {
                    closeProgressDialog()
                },
                onError = Consumer<Throwable> {
                    closeProgressDialog()
                    showErrorDialog(fragment.activity, it)
                }))
    }

    private fun showErrorDialog(context: Context, throwable: Throwable) {
        val builder = AlertDialog.Builder(context)
        if (throwable is ApiError) {
            val apiError: ApiError = throwable
            builder.setTitle("Error: " + apiError.apiStatus)
        }
        builder.setMessage(throwable.message)
                .setPositiveButton("Retry", { _, _ -> requestWeather() })
                .setNegativeButton("Cancel", { _, _ -> })
                .create()
                .show()
    }

    private fun showProgressDialog() {
        closeProgressDialog()
        progressDialog = ProgressDialog(fragment.activity)
        progressDialog?.setCancelable(false)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog?.show()
    }

    private fun closeProgressDialog() {
        progressDialog?.dismiss()
    }
}
