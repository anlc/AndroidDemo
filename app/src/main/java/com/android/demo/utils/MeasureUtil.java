package com.android.demo.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;

import com.android.demo.base.CustomApplication;

/**
 * Created by Administrator on 2018/3/5.
 */

public class MeasureUtil {

    public static int dp(int size) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, CustomApplication.context.getResources().getDisplayMetrics());
    }

    public static int sp(int size) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size, CustomApplication.context.getResources().getDisplayMetrics());
    }

    public static float textHeight(Paint paint, String message) {
        return textBounds(paint, message).height();
    }

    public static float textWidth(Paint paint, String message) {
        return paint.measureText(message, 0, message.length());
    }

    public static Rect textBounds(Paint paint, String message) {
        Rect rect = new Rect();
        paint.getTextBounds(message, 0, message.length(), rect);
        return rect;
    }
}
