package com.android.demo.activity.demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.demo.R;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2020/12/18
 */
public class BroadcastReceiverActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "BroadcastActivity";
    private static final String NORMAL_BROAD = "normal_broad";
    private static final String ORDER_BROAD = "order_broad";
    private static final String STICKER_BROAD = "sticker_broad";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        findViewById(R.id.btn_send_normal).setOnClickListener(this);
        findViewById(R.id.btn_send_order).setOnClickListener(this);
        findViewById(R.id.btn_send_sticky).setOnClickListener(this);
        registerReceivers();
    }

    private void registerReceivers() {
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive: 111");
            }
        }, new IntentFilter(NORMAL_BROAD));

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive: 222");
            }
        }, new IntentFilter(NORMAL_BROAD));

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive: order 111");
                setResultData("append string"); // 给下一个广播传递数据
            }
        }, new IntentFilter(ORDER_BROAD));

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive: order 222");
                intent.getStringExtra("key");// 获取原始数据
                getResultData(); // 获取上一个接受的广播传递的数据
                abortBroadcast(); // 终止继续传递广播
            }
        }, new IntentFilter(ORDER_BROAD));

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive: order 333");
            }
        }, new IntentFilter(ORDER_BROAD));

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive: sticker 111");
            }
        }, new IntentFilter(STICKER_BROAD));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_normal:
                sendBroadcast(new Intent(NORMAL_BROAD));
                break;
            case R.id.btn_send_order:
                sendOrderedBroadcast(new Intent(ORDER_BROAD), null, null, null, Activity.RESULT_OK, null, null);
                break;
            case R.id.btn_send_sticky:
                sendStickyBroadcast(new Intent(STICKER_BROAD));

                registerReceiver(new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        Log.d(TAG, "onReceive: sticker 222");
                    }
                }, new IntentFilter(STICKER_BROAD));
                break;
            default:
        }
    }

    public static class OrderBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: order == 555");
        }
    }
}
