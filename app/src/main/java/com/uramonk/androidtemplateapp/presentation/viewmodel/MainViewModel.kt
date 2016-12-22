package com.uramonk.androidtemplateapp.presentation.viewmodel

import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.uramonk.androidtemplateapp.R
import com.uramonk.androidtemplateapp.presentation.view.MainFragment

import timber.log.Timber

/**
 * Created by uramonk on 2016/06/18.
 */
class MainViewModel(private val activity: RxAppCompatActivity) : BaseViewModel(activity) {

    override fun onCreate() {
        super.onCreate()
        Timber.d("onCreate")
        commitFragment(activity, MainFragment.newInstance(), R.id.container)
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
