package com.uramonk.androidtemplateapp.viewmodel;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

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
}
