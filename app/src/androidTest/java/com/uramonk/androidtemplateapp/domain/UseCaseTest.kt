package com.uramonk.androidtemplateapp.domain

import com.uramonk.androidtemplateapp.domain.interactor.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.TestScheduler
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by uramonk on 2017/02/27.
 */
@RunWith(MockitoJUnitRunner::class) class UseCaseTest {
    private lateinit var useCase: UseCaseTestClass
    private lateinit var errorUseCase: UseCaseErrorTestClass
    private lateinit var testScheduler: TestScheduler
    private lateinit var exception: Exception

    @Before fun setUp() {
        testScheduler = TestScheduler()
        useCase = UseCaseTestClass(testScheduler, testScheduler)
        errorUseCase = UseCaseErrorTestClass(testScheduler, testScheduler)
        exception = Exception("Error")
    }

    @Test fun testBuildObservableUseCaseReturnCorrectResult() {
        useCase.execute(onNext = Consumer { assertThat(it).isTrue() })
        useCase.execute(onNext = Consumer { assertThat(it).isTrue() }, onError = Consumer {})
        useCase.execute(onNext = Consumer { assertThat(it).isTrue() }, onError = Consumer {},
                onCompleted = Action {})
        useCase.execute(DisposableObserverTestClass())

        testScheduler.triggerActions()
    }

    @Test fun testBuildObservableUseCaseReturnThrowableWhenErrorOccurred() {
        errorUseCase.execute(onNext = Consumer { },
                onError = Consumer { assertThat(it).isEqualTo(exception) })
        errorUseCase.execute(onNext = Consumer { },
                onError = Consumer { assertThat(it).isEqualTo(exception) },
                onCompleted = Action {})
        errorUseCase.execute(DisposableObserverErrorTestClass())
        testScheduler.triggerActions()
    }

    @Test fun testBuildObservableUseCaseWasDisposedWhenUseCaseDisposed() {
        val consumer: Consumer<Boolean> = Consumer {}
        val disposable: Disposable = useCase.execute(consumer)
        testScheduler.triggerActions()
        useCase.dispose()

        assertThat(disposable.isDisposed).isTrue()
    }

    private inner class UseCaseTestClass internal constructor(executionScheduler: Scheduler,
            postScheduler: Scheduler) : UseCase<Boolean>(executionScheduler, postScheduler) {

        override fun buildObservableUseCase(): Observable<Boolean> {
            return Observable.just(true)
        }
    }

    private inner class UseCaseErrorTestClass internal constructor(executionScheduler: Scheduler,
            postScheduler: Scheduler) : UseCase<Boolean>(executionScheduler, postScheduler) {

        override fun buildObservableUseCase(): Observable<Boolean> {
            return Observable.error(exception)
        }
    }

    private inner class DisposableObserverTestClass internal constructor() : DisposableObserver<Boolean>() {
        override fun onNext(t: Boolean?) {
            assertThat(t).isTrue()
        }

        override fun onError(e: Throwable?) {

        }

        override fun onComplete() {

        }
    }

    private inner class DisposableObserverErrorTestClass internal constructor() : DisposableObserver<Boolean>() {
        override fun onNext(t: Boolean?) {

        }

        override fun onError(e: Throwable?) {
            assertThat(e).isEqualTo(exception)
        }

        override fun onComplete() {

        }
    }
}
