package com.android.demo.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Administrator on 2018/3/5.
 */

public class MeasureUtil {

    public static int dp(Context context, int size) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, context.getResources().getDisplayMetrics());
    }

    public static int sp(Context context, int size) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size, context.getResources().getDisplayMetrics());
    }
}
