package com.uramonk.androidtemplateapp.viewmodel;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.uramonk.androidtemplateapp.R;
import com.uramonk.androidtemplateapp.view.MainFragment;

import timber.log.Timber;

/**
 * Created by uramonk on 2016/06/18.
 */
public class MainViewModel extends BaseViewModel {
    private RxAppCompatActivity activity;

    public MainViewModel(RxAppCompatActivity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        Timber.d("onCreate");
        commitFragment(activity, MainFragment.newInstance(), R.id.container);
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
