package com.uramonk.androidtemplateapp.data.error;

import com.uramonk.androidtemplateapp.data.entity.Data;
import io.reactivex.Observable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by uramonk on 2017/03/10.
 */

public class RxJava2ErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    public static RxJava2ErrorHandlingCallAdapterFactory create() {
        return new RxJava2ErrorHandlingCallAdapterFactory();
    }

    private final CallAdapter.Factory factory = RxJava2CallAdapterFactory.create();

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxJava2CallAdapter<>(factory.get(returnType, annotations, retrofit));
    }

    private static class RxJava2CallAdapter<T> implements CallAdapter<T, Observable<? extends Data>> {
        private final CallAdapter<T, ?> callAdapter;

        public RxJava2CallAdapter(CallAdapter<T, ?> callAdapter) {
            this.callAdapter = callAdapter;
        }

        @Override public Type responseType() {
            return callAdapter.responseType();
        }

        @Override public Observable<? extends Data> adapt(Call<T> call) {
            return ((Observable<Data>) callAdapter.adapt(call))
                    .onErrorResumeNext(new ErrorHandler());
        }
    }
}
