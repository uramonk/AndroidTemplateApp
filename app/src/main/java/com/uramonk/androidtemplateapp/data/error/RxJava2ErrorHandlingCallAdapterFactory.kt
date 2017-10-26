package com.uramonk.androidtemplateapp.data.error

import com.uramonk.androidtemplateapp.data.entity.Entity
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

/**
 * Created by uramonk on 2017/03/10.
 */

class RxJava2ErrorHandlingCallAdapterFactory : CallAdapter.Factory() {

    private val factory = RxJava2CallAdapterFactory.create()

    override fun get(returnType: Type, annotations: Array<Annotation>,
            retrofit: Retrofit): CallAdapter<*, *> {
        return RxJava2CallAdapter(factory.get(returnType, annotations, retrofit))
    }

    private class RxJava2CallAdapter<T>(
            private val callAdapter: CallAdapter<T, *>) : CallAdapter<T, Observable<out Entity>> {

        override fun responseType(): Type {
            return callAdapter.responseType()
        }
        @Suppress("unchecked_cast")
        override fun adapt(call: Call<T>): Observable<out Entity> {
            return (callAdapter.adapt(call) as Observable<Entity>)
                    .onErrorResumeNext(ErrorHandler())
        }
    }

    companion object {
        fun create(): RxJava2ErrorHandlingCallAdapterFactory {
            return RxJava2ErrorHandlingCallAdapterFactory()
        }
    }
}
