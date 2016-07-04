package com.uramonk.androidtemplateapp.viewmodel;

import com.trello.rxlifecycle.components.RxFragment;

import timber.log.Timber;

/**
 * Created by uramonk on 2016/07/04.
 */
public class NextFragmentViewModel extends BaseViewModel {
    private RxFragment fragment;

    public NextFragmentViewModel(RxFragment fragment) {
        super(fragment);
        this.fragment = fragment;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        Timber.d("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.d("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.d("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
    }
}
