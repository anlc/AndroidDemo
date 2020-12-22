package com.demo.task;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2020/11/26
 */
public class LaunchModeActivity extends AppCompatActivity {

    private static final String TAG = "LaunchModeActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText(getClass().getName());
        textView.setTextSize(30);
        setContentView(textView);
        Log.d(TAG, "onCreate: " + getClass().getSimpleName(), new Throwable());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: " + getClass().getSimpleName(), new Throwable());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: " + getClass().getSimpleName(), new Throwable());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + getClass().getSimpleName(), new Throwable());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + getClass().getSimpleName(), new Throwable());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + getClass().getSimpleName(), new Throwable());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + getClass().getSimpleName(), new Throwable());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + getClass().getSimpleName(), new Throwable());
    }
}
