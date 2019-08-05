package com.android.demo.widget.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.android.demo.R;
import com.android.demo.utils.factory.PaintFactory;

public class RoundImageView extends View {

    private Paint mPaint;
    private Bitmap bitmap;

    public RoundImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = PaintFactory.createFillPaint(Color.BLACK);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.timg);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);
    }
}
