package com.uramonk.androidtemplateapp.viewmodel;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.CallSuper;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by uramonk on 2016/06/19.
 */
public class BaseViewModel {
    public BaseViewModel(RxAppCompatActivity activity) {
        subscribeActivityLifecycle(activity, ActivityEvent.DESTROY);
    }

    public BaseViewModel(RxAppCompatActivity activity, ActivityEvent activityEvent) {
        subscribeActivityLifecycle(activity, activityEvent);
    }

    private void subscribeActivityLifecycle(RxAppCompatActivity activity, ActivityEvent activityEvent) {
        activity.lifecycle()
                .compose(activity.bindUntilEvent(activityEvent))
                .subscribe(activityEvent1 -> {
                    switch (activityEvent1) {
                        case CREATE:
                            onCreate();
                            break;
                        case START:
                            onStart();
                            break;
                        case RESUME:
                            onResume();
                            break;
                        case PAUSE:
                            onPause();
                            break;
                        case STOP:
                            onStop();
                            break;
                        case DESTROY:
                            onDestroy();
                            break;
                    }
                });
    }

    public BaseViewModel(com.trello.rxlifecycle.components.RxFragment fragment) {
        subscribeFragmentLifecycle(fragment, FragmentEvent.DESTROY_VIEW);
    }

    public BaseViewModel(com.trello.rxlifecycle.components.RxFragment fragment, FragmentEvent fragmentEvent) {
        subscribeFragmentLifecycle(fragment, fragmentEvent);
    }

    private void subscribeFragmentLifecycle(com.trello.rxlifecycle.components.RxFragment fragment, FragmentEvent fragmentEvent) {
        fragment.lifecycle()
                .compose(fragment.bindUntilEvent(fragmentEvent))
                .subscribe(fragmentEvent1 -> {
                    switch (fragmentEvent1) {
                        case CREATE:
                            onCreate();
                            break;
                        case CREATE_VIEW:
                            onCreateView();
                            break;
                        case ATTACH:
                            onAttach();
                            break;
                        case START:
                            onStart();
                            break;
                        case RESUME:
                            onResume();
                            break;
                        case PAUSE:
                            onPause();
                            break;
                        case STOP:
                            onStop();
                            break;
                        case DETACH:
                            onDetach();
                            break;
                        case DESTROY_VIEW:
                            onDestroyView();
                            break;
                        case DESTROY:
                            onDestroy();
                            break;
                    }
                });
    }

    @CallSuper
    protected void onCreate() {
        // Implement common process
    }

    @CallSuper
    protected void onCreateView() {

    }

    @CallSuper
    protected void onAttach() {

    }

    @CallSuper
    protected void onStart() {
        
    }

    @CallSuper
    protected void onResume() {

    }

    @CallSuper
    protected void onPause() {

    }

    @CallSuper
    protected void onStop() {

    }

    @CallSuper
    protected void onDetach() {

    }

    @CallSuper
    protected void onDestroyView() {

    }

    @CallSuper
    protected void onDestroy() {

    }

    protected void commitFragment(Activity activity, Fragment fragment, int container_id) {
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.replace(container_id, fragment, fragment.getClass().getSimpleName());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
