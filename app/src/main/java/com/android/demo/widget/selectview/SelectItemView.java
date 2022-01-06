package com.android.demo.widget.selectview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.android.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author anlc
 * @date 2020/5/19
 */
public class SelectItemView extends FrameLayout {

    private List<String> mListData;

    private Scroller mScroller;
    private int touchSlop;
    private int mTextColor;
    private float mTextSize;
    private int mItemPadding;

    private float mDownY;
    private OnSelectChangedListener mOnSelectChangedListener;

    public SelectItemView(Context context) {
        this(context, null);
    }

    public SelectItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.SelectItemView);
        mTextColor = typeArray.getColor(R.styleable.SelectItemView_item_text_color, Color.BLACK);
        mTextSize = typeArray.getDimension(R.styleable.SelectItemView_item_text_size, 13);
        mItemPadding = (int) typeArray.getDimension(R.styleable.SelectItemView_item_padding, 0);
        typeArray.recycle();

        mListData = new ArrayList<>();
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context, new DecelerateInterpolator());
    }

    public void setListData(List<String> listData) {
        this.mListData = listData;
        addItemView();
        requestLayout();
    }

    public void addItemView() {
        removeAllViews();
        for (String item : mListData) {
            TextView itemView = new TextView(getContext());
            itemView.setTextColor(mTextColor);
            itemView.setTextSize(mTextSize);
            itemView.setPadding(mItemPadding, mItemPadding, mItemPadding, mItemPadding);
            itemView.setText(item);
            addView(itemView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int centerY = getHeight() / 2;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int startY = getPaddingTop() + centerY - view.getHeight() / 2;
            view.layout(
                    getPaddingLeft(),
                    startY + view.getHeight() * i,
                    getWidth() - getPaddingRight(),
                    startY + view.getHeight() * (i + 1)
            );
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getRawY();
                return true;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) (mDownY - ev.getRawY());
                if (Math.abs(moveY) > touchSlop) {
                    scrollBy(0, moveY);
                    mDownY = ev.getRawY();
                }
                break;
            case MotionEvent.ACTION_UP:
                // 选中item的处理逻辑
                View firstChildView = getChildAt(0);
                int selectedIndex = Math.round(getScrollY() / (float) firstChildView.getHeight());

                if (selectedIndex < 0) {
                    selectedIndex = 0;
                } else if (selectedIndex >= mListData.size()) {
                    selectedIndex = mListData.size() - 1;
                }
                int centerY = getPaddingTop() + getHeight() / 2 - firstChildView.getHeight() / 2;
                int dy = (int) (centerY + getScrollY() - getChildAt(selectedIndex).getY());
                mScroller.startScroll(getScrollX(), getScrollY(), getScrollX(), -dy, 200);
                if (mOnSelectChangedListener != null) {
                    mOnSelectChangedListener.onSelected(selectedIndex, mListData.get(selectedIndex));
                }
                invalidate();
                break;
            default:
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        //判断是否还在滚动，还在滚动为true
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    public void setOnSelectChangedListener(OnSelectChangedListener onSelectChangedListener) {
        this.mOnSelectChangedListener = onSelectChangedListener;
    }

    public interface OnSelectChangedListener {
        /**
         * 滑动结束选中回调
         *
         * @param position   选中的position
         * @param selectItem 选中的 item data
         */
        void onSelected(int position, String selectItem);
    }
}
