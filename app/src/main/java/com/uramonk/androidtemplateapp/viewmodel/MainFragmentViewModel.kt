package com.uramonk.androidtemplateapp.viewmodel

import android.databinding.ObservableField
import android.view.View

import com.trello.rxlifecycle.FragmentEvent
import com.trello.rxlifecycle.components.RxFragment
import com.uramonk.androidtemplateapp.ModuleInjector
import com.uramonk.androidtemplateapp.R
import com.uramonk.androidtemplateapp.entity.WeatherEntity
import com.uramonk.androidtemplateapp.error.CommonErrorHandler
import com.uramonk.androidtemplateapp.repository.IWeatherRepository
import com.uramonk.androidtemplateapp.view.MainFragment
import com.uramonk.androidtemplateapp.view.NextFragment

import java.util.concurrent.TimeUnit

import javax.inject.Inject

import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import timber.log.Timber

/**
 * Created by uramonk on 2016/06/23.
 */
class MainFragmentViewModel(private val fragment: MainFragment) : BaseViewModel(fragment) {

    val text = ObservableField("")

    val weatherEntity = ObservableField<WeatherEntity>()

    @Inject
    lateinit var weatherRepository: IWeatherRepository

    override fun onCreate() {
        super.onCreate()
        Timber.d("onCreate")
    }

    override fun onCreateView() {
        super.onCreateView()
        Timber.d("onCreateView")
    }

    override fun onAttach() {
        super.onAttach()
        Timber.d("onAttach")
    }

    override fun onStart() {
        super.onStart()
        Timber.d("onStart")
        ModuleInjector.WeatherComponentInstance.get().inject(this)
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")

        subscribeSignals()
    }

    private fun subscribeSignals() {
        weatherRepository.getWeather()
                .compose(fragment.bindUntilEvent<Any>(FragmentEvent.PAUSE))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ value -> weatherEntity.set(value as WeatherEntity) }) { throwable ->
                    Timber.d(throwable, "Error: WeatherService.getWeather")
                    CommonErrorHandler().handleError(fragment, throwable)
                }

        fragment.onButtonClicked()
                .compose(fragment.bindUntilEvent<Any>(FragmentEvent.PAUSE))
                .subscribe {
                    if (text.get().isEmpty()) {
                        text.set("Button Clicked!")
                    } else {
                        text.set("")
                    }
                }

        fragment.onNextButtonClicked()
                .compose(fragment.bindUntilEvent<Any>(FragmentEvent.PAUSE))
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe {
                    commitFragment(fragment.activity, NextFragment.newInstance(),
                                    R.id.container)
                }
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("onStop")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.d("onDetach")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy")
    }
}
