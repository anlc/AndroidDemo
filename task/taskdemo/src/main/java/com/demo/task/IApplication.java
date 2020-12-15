package com.demo.task;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2020/11/23
 */
public class IApplication extends Application implements LifecycleEventObserver {

    private static final String TAG = "IApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        Log.d(TAG, "onStateChanged: state: " + source.getLifecycle().getCurrentState()
                + ", name:" + event.name());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onMoveToForeground() {
        Log.d(TAG, "onMoveToForeground: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onMoveToBackground() {
        Log.d(TAG, "onMoveToBackground: ");
    }
}
