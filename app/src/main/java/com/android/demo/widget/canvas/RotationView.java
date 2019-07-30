package com.android.demo.widget.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.android.demo.utils.factory.PaintFactory;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-06-12
 */
public class RotationView extends View {

    private Paint paint;
    private int hRadius = 0;
    private int mRadius = 0;

    public RotationView(Context context) {
        this(context, null);
    }

    public RotationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = PaintFactory.createStrokePaint(Color.BLACK);
        paint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float cx = getMeasuredWidth() / 2;
        float cy = getMeasuredHeight() / 2;
        float radius = cy;

        canvas.drawCircle(cx, cy, radius, paint);

        for (int i = 0; i < 60; i++) {
            int length = 10;
            if (i % 6 == 0) {
                length = 30;
            }
            canvas.drawLine(cx, 0, cy, length, paint);
            canvas.rotate(360 / 24, cx, cy);
        }

        canvas.save();

        canvas.translate(cx, cy);

        float hour = cx / 2;
        float minute = cx * 3 / 4;
        canvas.drawLine(0,
                0,
                (float) (hour * Math.cos(hRadius)),
                (float) (hour * Math.sin(hRadius)),
                paint);

        canvas.drawLine(0,
                0,
                (float) (minute * Math.cos(mRadius)),
                (float) (minute * Math.sin(mRadius)),
                paint);

        canvas.restore();

        mRadius++;
        if (mRadius % 10 == 0) {
            hRadius++;
        }

        postInvalidateDelayed(500);
    }
}
