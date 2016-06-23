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
    protected void onCreateView() {
        Timber.d("onCreateView");
    }

    @Override
    protected void onStartView() {
        Timber.d("onStartView");

        commitFragment(activity, MainFragment.newInstance(), R.id.container);
    }

    @Override
    protected void onResumeView() {
        Timber.d("onResumeView");
    }

    @Override
    protected void onPauseView() {
        Timber.d("onPauseView");
    }

    @Override
    protected void onStopView() {
        Timber.d("onStopView");
    }

    @Override
    protected void onDestroyView() {
        Timber.d("onDestroyView");
    }

}
