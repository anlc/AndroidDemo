package com.android.demo.widget.selectview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import com.android.demo.utils.MeasureUtil;
import com.android.demo.utils.factory.PaintFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */

public class SelectView extends View {

    private List<String> listData;

    private Paint textPaint;
    private float itemPadding;
    private Paint pointPaint;
    private int centerX;

    private Scroller scroller;
    private int touchSlop;
    private float lastTouchY;
    private float downY;
    private float moveY;

    public SelectView(Context context) {
        this(context, null);
    }

    public SelectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        listData = new ArrayList<>();
        pointPaint = PaintFactory.createFillPaint(Color.RED);
        pointPaint.setStrokeWidth(5);
        textPaint = PaintFactory.createFillPaint(Color.BLACK);
        textPaint.setTextSize(MeasureUtil.sp(15));
        itemPadding = MeasureUtil.dp(12);

        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        scroller = new Scroller(context, new DecelerateInterpolator());
    }

    public void setListData(List<String> listData) {
        this.listData = listData;
    }

    private float measureTextHeight(String text) {
        return MeasureUtil.textHeight(textPaint, text);
    }

    private float measureTextWidth(String text) {
        return MeasureUtil.textWidth(textPaint, text);
    }

    //获取文字的rect
    private Rect measureTextBound(String text) {
        return MeasureUtil.textBounds(textPaint, text);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX = getWidth() / 2;

        float tempHeight = itemPadding;
        for (int i = 0; i < listData.size(); i++) {
            String text = listData.get(i);
            Rect rect = measureTextBound(text);
            float x = centerX - measureTextWidth(text) / 2;
            tempHeight += rect.height() + itemPadding;
            canvas.drawText(text, x, tempHeight, textPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("tag", "- onTouchEvent ->" + ev.getAction());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getRawY();
                return true;
            case MotionEvent.ACTION_MOVE:
                moveY = downY - ev.getRawY();
                if (Math.abs(moveY) > touchSlop) {
                    setScrollY((int) (lastTouchY + moveY));
                }
                break;
            case MotionEvent.ACTION_UP:
                lastTouchY += moveY;
                scroller.startScroll(0, getScrollY(), 0, (int) moveY, 200);
                invalidate();
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        //判断是否还在滚动，还在滚动为true
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }else{
        }
        super.computeScroll();
    }
}
