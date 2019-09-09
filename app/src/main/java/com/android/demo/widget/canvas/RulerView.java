package com.android.demo.widget.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.android.demo.utils.factory.PaintFactory;

public class RulerView extends View {

    private final static int SIZE = 30;
    private Paint mPaint;

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = PaintFactory.createStrokePaint(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int cy = getMeasuredHeight() / 2;

        canvas.drawLine(0, cy, getWidth(), cy, mPaint);
        canvas.save();
        for (int i = 0; i < SIZE + 1; i++) {
            canvas.drawLine(0, cy - 20, 0, cy, mPaint);
            canvas.translate(getWidth() / SIZE, 0);
        }
    }
}
