package com.uramonk.androidtemplateapp.data.error;

import com.uramonk.androidtemplateapp.ModuleInjector;
import com.uramonk.androidtemplateapp.data.entity.Entity;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import java.io.IOException;
import java.lang.annotation.Annotation;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;

/**
 * Created by uramonk on 2017/03/10.
 */

public class ErrorHandler implements Function<Throwable, ObservableSource<? extends Entity>> {
    @Inject Retrofit retrofit;

    public ErrorHandler() {
        ModuleInjector.WeatherComponentInstance.INSTANCE.get().inject(this);
    }

    @Override public Observable<? extends Entity> apply(@NonNull Throwable throwable) throws Exception {

        if (throwable instanceof HttpException) {
            // 400 ~ 500
            return handleHttpException((HttpException)throwable);
        } else if (throwable instanceof IOException) {
            // NETWORK_ERROR
            // https://github.com/square/retrofit/issues/1260#issuecomment-154878865
            return handleIOException((IOException)throwable);
        }

        return Observable.error(throwable);
    }

    private Observable<? extends Entity> handleHttpException(HttpException exception) {
        Converter<ResponseBody, ApiError> errorConverter = retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);
        ApiError apiError;
        try {
            apiError = errorConverter.convert(exception.response().errorBody());
        } catch (IOException ioException) {
            apiError = new ApiError(ApiStatus.BAD_RESPONSE.getValue(), ioException.getMessage());
        }

        return Observable.error(apiError);
    }

    private Observable<? extends Entity> handleIOException(IOException exception) {
        ApiError apiError = new ApiError(ApiStatus.NETWORK_ERROR.getValue(), exception.getMessage());
        return Observable.error(apiError);
    }
}
