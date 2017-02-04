package com.uramonk.androidtemplateapp.presentation.viewmodel

import android.webkit.WebView
import com.uramonk.androidtemplateapp.presentation.view.LicenseFragment

import timber.log.Timber

class LicenseFragmentViewModel(private val fragment: LicenseFragment) : BaseViewModel(fragment) {

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

        val webview: WebView = fragment.getWebView()
        webview.loadUrl("file:///android_asset/licenses.html")
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
