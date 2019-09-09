package com.android.demo.utils.factory;

import android.graphics.Paint;
import androidx.annotation.ColorInt;

/**
 * Created by Administrator on 2018/4/4.
 */

public class PaintFactory {

    public static Paint createStrokePaint(@ColorInt int color){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        return paint;
    }

    public static Paint createFillPaint(@ColorInt int color){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        return paint;
    }
}
