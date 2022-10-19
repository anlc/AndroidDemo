package com.http.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";
    private Handler mHandler;
    private HandlerThread mHandlerThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TextView(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandlerThread = new HandlerThread("xx");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Log.i(TAG, "handleMessage: ==");
            }
        };
        Log.i(TAG, "onCreate: openCamera");
        openCamera();
        Log.i(TAG, "onCreate: end");
    }

    private void openCamera() {
        mHandler.obtainMessage(0).sendToTarget();
        waitDone();
    }

    private boolean waitDone() {
        final Object waitDoneLock = new Object();
        final Runnable unlockRunnable = () -> {
            synchronized (waitDoneLock) {
                Log.i(TAG, "waitDone: execute");
                waitDoneLock.notifyAll();
            }
        };

        synchronized (waitDoneLock) {
            Log.i(TAG, "waitDone: post runnable");
            mHandler.post(unlockRunnable);
            try {
                Log.i(TAG, "waitDone: wait");
                waitDoneLock.wait();
            } catch (InterruptedException ex) {
                Log.e(TAG, "waitDone interrupted");
                return false;
            }
        }
        Log.i(TAG, "waitDone: end");
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            Log.i(TAG, "onStop: start");
            mHandler.obtainMessage(1).sendToTarget();
            Log.i(TAG, "onStop: wait ");
            if (mHandlerThread != null) {
                mHandlerThread.quit();
                mHandlerThread.join();
                mHandlerThread = null;
                Log.i(TAG, "onStop: end ");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
