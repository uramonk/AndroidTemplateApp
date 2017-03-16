package com.uramonk.androidtemplateapp.domain.interactor

import com.uramonk.androidtemplateapp.Constants
import com.uramonk.androidtemplateapp.domain.model.WeatherList
import com.uramonk.androidtemplateapp.domain.repository.WeatherRepository
import com.uramonk.androidtemplateapp.domain.store.WeatherStore
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by kaz on 2016/12/23.
 */

class GetWeatherListUseCase
@Inject
constructor(private val weatherRepository: WeatherRepository,
        private val weatherStore: WeatherStore) : UseCase<WeatherList>() {

    override fun buildObservableUseCase(): Observable<WeatherList> {
        return weatherStore.getValue()
                .flatMap { it ->
                    if(System.currentTimeMillis() - it.createdAt > Constants.CACHE_INTERVAL) {
                        Timber.d("Get Weather from Network")
                        return@flatMap weatherRepository.getWeatherList()
                    }
                    Timber.d("Get Weather from Local store")
                    return@flatMap Observable.just(it)
                }
                .doOnNext { weatherStore.update(it) }
    }
}
