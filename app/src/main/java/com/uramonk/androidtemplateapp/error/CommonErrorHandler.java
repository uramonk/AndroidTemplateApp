package com.uramonk.androidtemplateapp.error;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.uramonk.androidtemplateapp.ModuleInjector;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by uramonk on 2016/06/22.
 */
public class CommonErrorHandler {
    @Inject
    Retrofit retrofit;

    public CommonErrorHandler() {
        ModuleInjector.getInstance().getWeatherComponent().inject(this);
    }

    public void handleError(Fragment fragment, Throwable throwable) {
        handleError(fragment.getActivity(), throwable);
    }

    public void handleError(Context context, Throwable throwable) {
        // 400 ~ 500
        if (throwable instanceof HttpException) {
            handleHttpException(context, (HttpException) throwable);
        }
        // NETWORK_ERROR
        // https://github.com/square/retrofit/issues/1260#issuecomment-154878865
        else if(throwable instanceof IOException){
            handleIOException(context, (IOException) throwable);
        }
    }

    private void handleHttpException(Context context, HttpException exception) {
        Converter<ResponseBody, APIError> errorConverter = retrofit.responseBodyConverter(APIError.class, new Annotation[0]);
        APIError apiError;
        try {
            apiError = errorConverter.convert(exception.response().errorBody());
        } catch (IOException e) {
            apiError = new APIError(APIStatus.BAD_RESPONSE.getValue(), e.getMessage());
        }

        show(context, apiError);
    }

    private void handleIOException(Context context, IOException exception) {
        APIError apiError = new APIError(APIStatus.NETWORK_ERROR.getValue(), exception.getMessage());
        show(context, apiError);
    }

    private void show(Context context, APIError apiError) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error: " + apiError.getAPIStatus());
        builder.setMessage(apiError.message);
        builder.setPositiveButton("OK",
                (dialog, which) -> {
                })
                .create()
                .show();
    }
}
