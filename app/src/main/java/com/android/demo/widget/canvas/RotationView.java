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

    public RotationView(Context context) {
        this(context, null);
    }

    public RotationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = PaintFactory.createStrokePaint(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        canvas.drawCircle(width / 2, height / 2, height / 2, paint);
    }
}
