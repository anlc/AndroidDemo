package com.android.demo.activity.demo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.demo.R;
import com.android.demo.service.LocalService;
import com.example.service.IRemoteInterface;

public class AidlActivity extends AppCompatActivity implements View.OnClickListener {

    private IRemoteInterface iRemoteInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);
        findViewById(R.id.bind).setOnClickListener(this);
        findViewById(R.id.unbind).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                startService(new Intent(this, LocalService.class));
                break;
            case R.id.stop:
                stopService(new Intent(this, LocalService.class));
                break;
            case R.id.bind:
                bindRemoteService();
//                bindService(startService, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                unbindRemoteService();
//                unbindService(connection);
                break;
        }
    }

    private void bindRemoteService() {
        Intent intent = new Intent();
        intent.setAction("com.example.service.RemoteService");
        intent.setPackage("com.example.service");
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    private void unbindRemoteService() {
        unbindService(connection);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
//            binder.getString();

            try {
                iRemoteInterface = IRemoteInterface.Stub.asInterface(service);
                iRemoteInterface.getRemoteString();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iRemoteInterface = null;
        }
    };
}
