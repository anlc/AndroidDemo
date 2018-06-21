package com.android.demo.utils;

import android.widget.Toast;

import com.android.demo.base.CustomApplication;

/**
 * Created by Administrator on 2018/6/21.
 */

public class Utils {

    public static void toast(String info) {
        Toast.makeText(CustomApplication.context, info, Toast.LENGTH_SHORT).show();
    }
}
