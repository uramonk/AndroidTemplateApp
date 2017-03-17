package com.uramonk.androidtemplateapp.data.error

import com.uramonk.androidtemplateapp.ModuleInjector
import com.uramonk.androidtemplateapp.data.entity.Entity
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Function
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException
import retrofit2.Retrofit

/**
 * Created by uramonk on 2017/03/10.
 */

class ErrorHandler : Function<Throwable, ObservableSource<out Entity>> {
    @Inject lateinit var retrofit: Retrofit

    init {
        ModuleInjector.SingletonWeatherComponent.instance.inject(this)
    }

    @Throws(Exception::class)
    override fun apply(@NonNull throwable: Throwable): Observable<out Entity> {

        if (throwable is HttpException) {
            // 400 ~ 500
            return handleHttpException(throwable)
        } else if (throwable is IOException) {
            // NETWORK_ERROR
            // https://github.com/square/retrofit/issues/1260#issuecomment-154878865
            return handleIOException(throwable)
        }

        return Observable.error(throwable)
    }

    private fun handleHttpException(exception: HttpException): Observable<out Entity> {
        val errorConverter = retrofit.responseBodyConverter<ApiError>(ApiError::class.java,
                arrayOfNulls<Annotation>(0))
        var apiError: ApiError
        try {
            apiError = errorConverter.convert(exception.response().errorBody())
        } catch (ioException: IOException) {
            apiError = ApiError(ApiStatus.BAD_RESPONSE.value, ioException.message)
        }

        return Observable.error(apiError)
    }

    private fun handleIOException(exception: IOException): Observable<out Entity> {
        val apiError = ApiError(ApiStatus.NETWORK_ERROR.value, exception.message)
        return Observable.error(apiError)
    }
}
