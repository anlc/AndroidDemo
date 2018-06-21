package com.android.demo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;

import com.android.demo.R;
import com.android.demo.utils.MeasureUtil;
import com.android.demo.utils.factory.PaintFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class MultiSquareEditView  extends EditText {

    private InputMethodManager inputManager;
    private List<String> dataList;
    private int oldStrLength;

    private float squareWidth;
    private float squareHeight;
    private float squareItemMargin;
    private int squareCount;
    private Paint bgPaint;
    private Paint textPaint;
    private Paint cursorPaint;
    private boolean cursorVisible = false;

    private InputCompleteListener inputCompleteListener;

    public void setOnInputCompleteListener(InputCompleteListener inputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener;
    }

    public interface InputCompleteListener {
        void inputComplete(String inputText);
    }

    public MultiSquareEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MultiSquareEditView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(final Context context, AttributeSet attrs) {
        inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MultiSquareEditView);
        squareCount = array.getInt(R.styleable.MultiSquareEditView_square_count, 6);
        squareWidth = array.getDimension(R.styleable.MultiSquareEditView_item_width, MeasureUtil.dp(30));
        squareHeight = array.getDimension(R.styleable.MultiSquareEditView_item_height, MeasureUtil.dp(30));
        squareItemMargin = array.getDimension(R.styleable.MultiSquareEditView_item_margin, MeasureUtil.dp(8));
        array.recycle();
        bgPaint = PaintFactory.createFillPaint(Color.WHITE);
        cursorPaint = PaintFactory.createFillPaint(Color.BLACK);
        cursorPaint.setStrokeWidth(MeasureUtil.dp(1));
        textPaint = PaintFactory.createStrokePaint(getResources().getColor(R.color.color_333333));
        textPaint.setStrokeWidth(MeasureUtil.dp(2));
        textPaint.setTextSize(MeasureUtil.sp(22));
        dataList = new ArrayList<>();

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                oldStrLength = s.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (dataList.size() < squareCount && s.length() > 0 && s.length() > oldStrLength) {
                    dataList.add(String.valueOf(s.charAt(s.length() - 1)));
                }
                postInvalidate();
                if (dataList.size() == squareCount && inputCompleteListener != null) {
                    inputCompleteListener.inputComplete(getString());
                }
            }
        });
        startPost();

        LocalMultiChoiceModeListener listener = new LocalMultiChoiceModeListener();
        setCustomSelectionActionModeCallback(listener);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            setCustomInsertionActionModeCallback(listener);
        }
    }

    //获取输入的验证码
    public String getString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; dataList != null && i < dataList.size(); i++) {
            builder.append(dataList.get(i));
        }
        return builder.toString();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL && !dataList.isEmpty()) {
            dataList.remove(dataList.size() - 1);
            postInvalidate();
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int start = (int) (width - squareCount * (squareWidth + squareItemMargin * 2)) / 2;
        float radius = MeasureUtil.dp(3);
        for (int i = 0; i < squareCount; i++) {
            RectF rectF = getRect(start, i);
            canvas.drawRoundRect(rectF, radius, radius, bgPaint);
            if (i < dataList.size()) {
                float x = rectF.left + squareWidth / 2 - textWidth(textPaint, dataList.get(i)) / 2;
                float y = rectF.top + squareHeight / 2 + textHeight(textPaint, dataList.get(i)) / 2;
                canvas.drawText(dataList.get(i), x, y, textPaint);
            }
        }

        if (cursorVisible && dataList.size() < squareCount) {
            int i = dataList.size();
            RectF rectF = getRect(start, i);
            if (flag) {
                cursorPaint.setColor(Color.BLACK);
            } else {
                cursorPaint.setColor(Color.WHITE);
            }
            canvas.drawLine(rectF.left + squareWidth / 2,
                    rectF.top + MeasureUtil.dp(10),
                    rectF.left + squareWidth / 2,
                    rectF.bottom - MeasureUtil.dp(10), cursorPaint);
            flag = !flag;
        }
    }

    private RectF getRect(int start, int i) {
        float left = start + (i + 1) * squareItemMargin + i * squareWidth + (i * squareItemMargin);
        float top = getPaddingTop() + getHeight() / 2 - squareHeight / 2;
        float right = left + squareWidth;
        float bottom = top + squareHeight;
        return new RectF(left, top, right, bottom);
    }

    boolean flag = true;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            postInvalidate();
        }
    };

    private void startPost() {
        handler.postDelayed(runnable, 1000);
    }

    private void stopPost() {
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopPost();
    }

    public void clearText() {
        if (dataList != null)
            dataList.clear();
        setText("");
    }

    protected float textHeight(Paint paint, String message) {
        return textBounds(paint, message).height();
    }

    protected float textWidth(Paint paint, String message) {
        return paint.measureText(message, 0, message.length());
    }

    private Rect textBounds(Paint paint, String message) {
        Rect rect = new Rect();
        paint.getTextBounds(message, 0, message.length(), rect);
        return rect;
    }

    @Override
    public void setCursorVisible(boolean cursorVisible) {
        this.cursorVisible = cursorVisible;
    }


    class LocalMultiChoiceModeListener implements AbsListView.MultiChoiceModeListener {

        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    }
}

