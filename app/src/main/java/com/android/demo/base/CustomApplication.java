package com.android.demo.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2018/2/9.
 */

public class CustomApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
