package com.uramonk.androidtemplateapp.viewmodel;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;
import com.uramonk.androidtemplateapp.R;

/**
 * Created by uramonk on 2016/06/19.
 */
public class BaseViewModel {
    public BaseViewModel(RxAppCompatActivity activity) {
        activity.lifecycle()
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(activityEvent -> {
                    switch (activityEvent) {
                        case CREATE:
                            onCreateView();
                            break;
                        case START:
                            onStartView();
                            break;
                        case RESUME:
                            onResumeView();
                            break;
                        case PAUSE:
                            onPauseView();
                            break;
                        case STOP:
                            onStopView();
                            break;
                        case DESTROY:
                            onDestroyView();
                            break;
                    }
                });
    }

    public BaseViewModel(com.trello.rxlifecycle.components.RxFragment fragment) {
        fragment.lifecycle()
                .compose(fragment.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(fragmentEvent -> {
                    switch (fragmentEvent) {
                        case CREATE:
                            onCreateView();
                            break;
                        case START:
                            onStartView();
                            break;
                        case RESUME:
                            onResumeView();
                            break;
                        case PAUSE:
                            onPauseView();
                            break;
                        case STOP:
                            onStopView();
                            break;
                        case DESTROY:
                            onDestroyView();
                            break;
                    }
                });
    }

    protected void onCreateView() {

    }

    protected void onStartView() {

    }

    protected void onResumeView() {

    }

    protected void onPauseView() {

    }

    protected void onStopView() {

    }

    protected void onDestroyView() {

    }

    protected void commitFragment(Activity activity, Fragment fragment, int container_id) {
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.replace(container_id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
