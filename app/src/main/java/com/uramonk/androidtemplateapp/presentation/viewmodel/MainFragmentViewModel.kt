package com.uramonk.androidtemplateapp.presentation.viewmodel

import android.databinding.ObservableField
import com.trello.rxlifecycle.FragmentEvent
import com.uramonk.androidtemplateapp.ModuleInjector
import com.uramonk.androidtemplateapp.R
import com.uramonk.androidtemplateapp.domain.interactor.DefaultSubscriber
import com.uramonk.androidtemplateapp.domain.interactor.UseCase
import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.error.CommonErrorHandler
import com.uramonk.androidtemplateapp.presentation.model.WeatherListModel
import com.uramonk.androidtemplateapp.presentation.model.mapper.WeatherListModelDataMapper
import com.uramonk.androidtemplateapp.presentation.view.LicenseFragment
import com.uramonk.androidtemplateapp.presentation.view.MainFragment
import com.uramonk.androidtemplateapp.presentation.view.NextFragment
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by uramonk on 2016/06/23.
 */
class MainFragmentViewModel(private val fragment: MainFragment) : BaseViewModel(fragment) {

    val text = ObservableField("")
    val weatherList = ObservableField<WeatherListModel>()

    @Inject
    lateinit var weatherUseCase: UseCase<WeatherList>

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
        weatherUseCase.execute(WeatherListSubscriber())

        fragment.onButtonClicked()
                .compose(fragment.bindUntilEvent<Void>(FragmentEvent.PAUSE))
                .subscribe {
                    if (text.get().isEmpty()) {
                        text.set("Button Clicked!")
                    } else {
                        text.set("")
                    }
                }

        fragment.onNextButtonClicked()
                .compose(fragment.bindUntilEvent<Void>(FragmentEvent.PAUSE))
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe {
                    commitFragment(fragment.activity, NextFragment.newInstance(),
                            R.id.container)
                }

        fragment.onLicenseButtonClicked()
                .compose(fragment.bindUntilEvent<Void>(FragmentEvent.PAUSE))
                .subscribe {
                    commitFragment(fragment.activity, LicenseFragment.newInstance(),
                            R.id.container)
                }
    }

    private inner class WeatherListSubscriber : DefaultSubscriber<WeatherList>() {
        override fun onNext(t: WeatherList) {
            val weatherListModel: WeatherListModel = WeatherListModelDataMapper().transform(t)
            weatherList.set(weatherListModel)
        }

        override fun onError(throwable: Throwable) {
            Timber.e(throwable, "Error: WeatherService.getWeatherList")
            CommonErrorHandler().handleError(fragment, throwable)
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
        weatherUseCase.unsubscribe()
    }
}
