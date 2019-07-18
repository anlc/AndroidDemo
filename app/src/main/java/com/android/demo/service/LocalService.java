package com.android.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class LocalService extends Service {

    private final static String TAG = "LocalService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "  ----->  onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "  ----->  onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "  ----->  onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    public class LocalBinder extends Binder {

        public void getString() {
            Log.d(TAG, "  ----->  getString");
        }
    }
}
