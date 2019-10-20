package com.android.demo.widget.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.demo.utils.factory.PaintFactory;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-09-30
 */
public class RectView extends View {

    private Paint srcPaint;
    private Paint dcsPaint;
    private Paint intersectPaint;
    private Paint unionPaint;
    private Rect src;
    private Rect dcs;
    private Rect intersectSrc;
    private Rect unionSrc;

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        src = new Rect(0, 0, 100, 100);
        dcs = new Rect(50, 50, 200, 200);
        intersectSrc = new Rect(src);
        unionSrc = new Rect(src);

        srcPaint = PaintFactory.createFillPaint(Color.BLUE);
        dcsPaint = PaintFactory.createFillPaint(Color.GRAY);
        intersectPaint = PaintFactory.createFillPaint(Color.RED);
        unionPaint = PaintFactory.createFillPaint(Color.GREEN);

        BitmapFactory.decodeFile("");

        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_4444);
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            System.gc();
            bitmap = null;
        }

        Paint paint = PaintFactory.createFillPaint(Color.GREEN);
        paint.setTextSize(12);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        unionSrc.union(dcs);
        canvas.drawRect(unionSrc, unionPaint);
        canvas.drawRect(src, srcPaint);
        canvas.drawRect(dcs, dcsPaint);
        intersectSrc.intersect(dcs);
        canvas.drawRect(intersectSrc, intersectPaint);
    }
}
