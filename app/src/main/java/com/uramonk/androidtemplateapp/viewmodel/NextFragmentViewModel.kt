package com.uramonk.androidtemplateapp.viewmodel

import com.trello.rxlifecycle.components.RxFragment

import timber.log.Timber

/**
 * Created by uramonk on 2016/07/04.
 */
class NextFragmentViewModel(private val fragment: RxFragment) : BaseViewModel(fragment) {

    override fun onCreate() {
        super.onCreate()
        Timber.d("onCreate")
    }

    override fun onStart() {
        super.onStart()
        Timber.d("onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy")
    }
}
