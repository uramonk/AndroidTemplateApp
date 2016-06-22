package com.uramonk.androidtemplateapp.error;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import timber.log.Timber;

/**
 * Created by uramonk on 2016/06/22.
 */
public class CommonErrorHandler {
    public static void handleError(Context context, Throwable throwable, Retrofit retrofit) {
        if (throwable instanceof HttpException) {
            handleHttpException(context, (HttpException) throwable, retrofit);
        }
    }

    private static void handleHttpException(Context context, HttpException httpException, Retrofit retrofit) {
        Converter<ResponseBody, APIError> errorConverter = retrofit.responseBodyConverter(APIError.class, new Annotation[0]);
        APIError apiError;
        try {
            apiError = errorConverter.convert(httpException.response().errorBody());

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Error: " + apiError.cod);
            builder.setMessage(apiError.message);
            builder.setPositiveButton("OK",
                    (dialog, which) -> {
                    })
                    .create()
                    .show();
        } catch (IOException e) {
            Timber.e(e, "Cannot convert API Error.");
        }


    }
}
