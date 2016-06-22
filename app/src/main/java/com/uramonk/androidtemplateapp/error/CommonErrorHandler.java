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
        // 400 ~ 500
        if (throwable instanceof HttpException) {
            handleHttpException(context, (HttpException) throwable, retrofit);
        }
        // NETWORK_ERROR
        // https://github.com/square/retrofit/issues/1260#issuecomment-154878865
        else if(throwable instanceof IOException){
            handleIOException(context, (IOException) throwable);
        }
    }

    private static void handleHttpException(Context context, HttpException exception, Retrofit retrofit) {
        Converter<ResponseBody, APIError> errorConverter = retrofit.responseBodyConverter(APIError.class, new Annotation[0]);
        APIError apiError;
        try {
            apiError = errorConverter.convert(exception.response().errorBody());
        } catch (IOException e) {
            apiError = new APIError(APIStatus.BAD_RESPONSE.getValue(), e.getMessage());
        }

        show(context, apiError);
    }

    private static void handleIOException(Context context, IOException exception) {
        APIError apiError = new APIError(APIStatus.NETWORK_ERROR.getValue(), exception.getMessage());
        show(context, apiError);
    }

    private static void show(Context context, APIError apiError) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error: " + apiError.apiStatus);
        builder.setMessage(apiError.message);
        builder.setPositiveButton("OK",
                (dialog, which) -> {
                })
                .create()
                .show();
    }
}
