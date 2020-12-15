package com.demo.task;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2020/11/23
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivityLifecyle";
    private CheckBox mCbStartRemote;
    private CheckBox mCbNewTask;
    private CheckBox mCbClearTop;
    private CheckBox mCbClearTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: " + isBackground());

        findViewById(R.id.btn_start_standard).setOnClickListener(this);
        findViewById(R.id.btn_start_single_top).setOnClickListener(this);
        findViewById(R.id.btn_start_single_task).setOnClickListener(this);
        findViewById(R.id.btn_start_single_instance).setOnClickListener(this);
        mCbStartRemote = findViewById(R.id.cb_start_remote);
        mCbNewTask = findViewById(R.id.cb_new_task);
        mCbClearTop = findViewById(R.id.cb_clear_top);
        mCbClearTask = findViewById(R.id.cb_clear_task);

        Log.d(TAG, "onCreate: " + getClass().getSimpleName());
    }

    public boolean isBackground() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = manager.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = ((ActivityManager.RunningTaskInfo) tasks.get(0)).topActivity;
            if (!topActivity.getPackageName().equals(getPackageName())) {
                return true;
            }
        }

        return false;
    }

    @Override
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        Intent intent = new Intent();
        Class clazz = null;
        switch (v.getId()) {
            case R.id.btn_start_standard:
                clazz = StandardActivity.class;
                break;
            case R.id.btn_start_single_top:
                clazz = SingleTopActivity.class;
                break;
            case R.id.btn_start_single_task:
                clazz = SingleTaskActivity.class;
                break;
            case R.id.btn_start_single_instance:
                clazz = SingleInstanceActivity.class;
                break;
            default:
        }
        if (mCbStartRemote.isChecked()) {
            intent.setComponent(new ComponentName("com.task.remote",
                    "com.task.remote." + clazz.getSimpleName()));
        } else {
            intent.setClass(this, clazz);
        }

        if (mCbNewTask.isChecked()) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (mCbClearTop.isChecked()) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        if (mCbClearTask.isChecked()) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: " + getClass().getSimpleName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: " + getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + getClass().getSimpleName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + getClass().getSimpleName());
    }
}
