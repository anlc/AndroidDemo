package com.android.demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.android.demo.R;
import com.android.demo.utils.factory.PaintFactory;

/**
 * Created by Administrator on 2018/1/4.
 */

public class DashLineView extends View {

    private Paint linePaint;
    private Path linePath;

    public DashLineView(Context context) {
        this(context, null);
    }

    public DashLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        linePaint = PaintFactory.createStrokePaint(getResources().getColor(R.color.color_666666));
        linePaint.setStrokeWidth(size());
        linePaint.setPathEffect(new DashPathEffect(new float[]{6, 5}, 0));
        linePath = new Path();
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, size());
    }

    private int size() {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.5f, getResources().getDisplayMetrics());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        linePath.moveTo(getPaddingLeft(), 0);
        linePath.lineTo(getMeasuredWidth() - getPaddingRight(), 0);
        canvas.drawPath(linePath, linePaint);
    }
}
