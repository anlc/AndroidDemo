package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class RemoteService extends Service {

    private final static String TAG = "RemoteService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "  ----->  onCreate :: RemoteService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "  ----->  onStartCommand :: RemoteService");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "  ----->  onDestroy :: RemoteService");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new AidlBinder();
    }

    class AidlBinder extends com.example.service.IRemoteInterface.Stub {

        @Override
        public void getRemoteString() throws RemoteException {
            Log.d(TAG, "  ----->  getRemoteString :: RemoteService");
        }
    }
}
