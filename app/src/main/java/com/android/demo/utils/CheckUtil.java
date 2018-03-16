package com.android.demo.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.android.demo.base.CustomApplication;

/**
 * Created by Administrator on 2018/2/9.
 */

public class CheckUtil {

    public static boolean isNull(Object obj) {
        return isNull(obj, "获取数据失败");
    }

    public static boolean isNull(Object obj, String hintText) {
        if (obj == null) {
            Toast.makeText(CustomApplication.context, hintText, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public static boolean isNull(String string, String hintText) {
        if (TextUtils.isEmpty(string)) {
            Toast.makeText(CustomApplication.context, hintText, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public static boolean isNull(String string) {
        return isNull(string, "获取数据失败");
    }
}
