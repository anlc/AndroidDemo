package com.android.demo.widget.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.demo.utils.factory.PaintFactory;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-11-04
 */
public class ClockView extends View {

    private Paint paint;
    private float radius;
    private float centerX;
    private float centerY;
    private float lineLength;
    private Calendar calendar;

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = PaintFactory.createStrokePaint(Color.BLACK);
        paint.setStrokeWidth(5);
        lineLength = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());

        calendar = Calendar.getInstance();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = Math.min(w, h) / 2 - 50;
        centerX = (w - 100) / 2 + 50;
        centerY = (h - 100) / 2 + 50;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                postInvalidate();
            }
        };
        new Timer().schedule(task, 1000, 1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(centerX, centerY, radius, paint);
        float startY = centerY - radius;
        canvas.save();
        for (int i = 0; i < 60; i++) {
            float stopY = i % 5 == 0 ? startY + lineLength * 2 : startY + lineLength;
            canvas.drawLine(centerX, startY, centerX, stopY, paint);
            canvas.rotate(6, centerX, centerY);
        }
        canvas.restore();
        canvas.drawPoint(centerX, centerY, paint);

        calendar.setTimeInMillis(System.currentTimeMillis());

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        drawLine(canvas, 360 / 12 * hour, 0.5f);
        drawLine(canvas, 360 / 60 * minute, 0.6f);
        drawLine(canvas, 360 / 60 * second, 0.7f);
    }

    private void drawLine(Canvas canvas, int degree, float widthRatio) {
        double radian = Math.toRadians(degree);
        int endX = (int) (centerX + radius * widthRatio * Math.cos(radian));
        int endY = (int) (centerY + radius * widthRatio * Math.sin(radian));

        canvas.save();
        canvas.rotate(-90, centerX, centerY);
        canvas.drawLine(centerX, centerY, endX, endY, paint);
        canvas.restore();
    }
}
