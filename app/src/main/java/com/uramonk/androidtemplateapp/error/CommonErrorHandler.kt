package com.uramonk.androidtemplateapp.error

import android.app.Fragment
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog

import com.uramonk.androidtemplateapp.ModuleInjector

import java.io.IOException

import javax.inject.Inject

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.HttpException

/**
 * Created by uramonk on 2016/06/22.
 */
class CommonErrorHandler {
    @Inject
    lateinit var retrofit: Retrofit

    init {
        ModuleInjector.WeatherComponentInstance.get().inject(this)
    }

    fun handleError(fragment: Fragment, throwable: Throwable) {
        handleError(fragment.activity, throwable)
    }

    fun handleError(context: Context, throwable: Throwable) {
        // 400 ~ 500
        if (throwable is HttpException) {
            handleHttpException(context, throwable)
        } else if (throwable is IOException) {
            handleIOException(context, throwable)
        }// NETWORK_ERROR
        // https://github.com/square/retrofit/issues/1260#issuecomment-154878865
    }

    private fun handleHttpException(context: Context, exception: HttpException) {
        val errorConverter = retrofit.responseBodyConverter<ApiError>(ApiError::class.java, arrayOfNulls<Annotation>(0))
        var apiError: ApiError
        try {
            apiError = errorConverter.convert(exception.response().errorBody())
        } catch (e: IOException) {
            apiError = ApiError(ApiStatus.BAD_RESPONSE.value, e.message)
        }

        show(context, apiError)
    }

    private fun handleIOException(context: Context, exception: IOException) {
        val apiError = ApiError(ApiStatus.NETWORK_ERROR.value, exception.message)
        show(context, apiError)
    }

    private fun show(context: Context, apiError: ApiError) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error: " + apiError.apiStatus)
        builder.setMessage(apiError.message)
        builder.setPositiveButton("OK"
        ) { dialog, which -> }
                .create()
                .show()
    }
}
