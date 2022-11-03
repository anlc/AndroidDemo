package com.android.demo.widget.selectview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
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

    private static final String TAG = "SelectItemView1";

    private List<String> mListData;

    private Scroller mScroller;
    private int touchSlop;
    private int mTextColor;
    private float mTextSize;
    private int mSelectTextColor;
    private float mSelectTextSize;
    private int mItemPadding;
    private float mDownY;

    private OnSelectChangedListener mOnSelectChangedListener;
    private TextView mSelectedView;
    private int mSelectIndex;

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
        mSelectTextColor = typeArray.getColor(R.styleable.SelectItemView_select_text_color, mTextColor);
        mTextSize = typeArray.getDimension(R.styleable.SelectItemView_item_text_size, 13);
        mSelectTextSize = typeArray.getDimension(R.styleable.SelectItemView_select_text_size, mTextSize);
        mItemPadding = (int) typeArray.getDimension(R.styleable.SelectItemView_item_padding, 0);
        typeArray.recycle();

        mListData = new ArrayList<>();
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context, new DecelerateInterpolator());
    }

    public void setListData(List<String> listData) {
        this.mListData = listData;
        addItemView();
        setSelectItem(0);
        requestLayout();
    }

    public void addItemView() {
        removeAllViews();

        for (int i = 0; i < mListData.size(); i++) {
            TextView itemView = new TextView(getContext());
            itemView.setTextColor(mTextColor);
            itemView.setTextSize(mTextSize);
            itemView.setPadding(mItemPadding, mItemPadding, mItemPadding, mItemPadding);
            itemView.setText(mListData.get(i));
            itemView.setGravity(Gravity.CENTER);
            addView(itemView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
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
                    startY + t,
                    getWidth() - getPaddingRight(),
                    startY + t + view.getHeight()
            );
            t += view.getHeight();
        }
    }

    private boolean mIsMoved;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onTouchEvent: " + ev.getAction());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getRawY();
                mIsMoved = false;
                return true;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) (mDownY - ev.getRawY());
                if (Math.abs(moveY) > touchSlop) {
                    scrollBy(0, moveY);
                    mDownY = ev.getRawY();
                    mIsMoved = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                // 松开手指后，滑动到选中item的处理逻辑
                Log.i(TAG, "onTouchEvent: mIsMoved: " + mIsMoved);
                if (mIsMoved) {
                    View firstChildView = getChildAt(0);
                    int selectedIndex = Math.round(getScrollY() / (float) firstChildView.getHeight());

                    if (selectedIndex < 0) {
                        selectedIndex = 0;
                    } else if (selectedIndex >= mListData.size()) {
                        selectedIndex = mListData.size() - 1;
                    }
                    int startY = getPaddingTop() + getHeight() / 2 - firstChildView.getHeight() / 2;
                    int dy = (int) (startY + getScrollY() - getChildAt(selectedIndex).getY());
                    mScroller.startScroll(getScrollX(), getScrollY(), getScrollX(), -dy, 200);
                    setSelectItem(selectedIndex);
                    invalidate();
                } else {
                    View child = getChildAt(0);
                    int startY = getPaddingTop() + getHeight() / 2 - child.getHeight() / 2;
                    int index = Math.round((ev.getRawY() - startY) / child.getHeight()) + mSelectIndex - 1;
                    Log.i(TAG, "onTouchEvent: index: " + index);
                    smoothScrollToIndex(index);
                }
                break;
            default:
        }
        return super.onTouchEvent(ev);
    }

    public void smoothScrollToIndex(int index) {
        View child = getChildAt(index);
        int startY = getPaddingTop() + getHeight() / 2 - child.getHeight() / 2;
        int dy = (int) (startY + getScrollY() - child.getY());
        mScroller.startScroll(getScrollX(), getScrollY(), getScrollX(), -dy, 200);
        setSelectItem(index);
        invalidate();
    }

    private void setSelectItem(int selectedIndex) {
        mSelectIndex = selectedIndex;
        if (mSelectedView != null) {
            mSelectedView.setTextColor(mTextColor);
            mSelectedView.setTextSize(mTextSize);
        }
        mSelectedView = (TextView) getChildAt(selectedIndex);
        mSelectedView.setTextColor(mSelectTextColor);
        mSelectedView.setTextSize(mSelectTextSize);

        if (mOnSelectChangedListener != null) {
            mOnSelectChangedListener.onSelected(selectedIndex, mListData.get(selectedIndex));
        }
    }

    @Override
    public void computeScroll() {
        // 判断是否还在滚动，还在滚动为true
        Log.i(TAG, "computeScroll: " + mScroller.computeScrollOffset() + ", currY: " + mScroller.getCurrY());
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
         * 滑动结束选中后回调
         *
         * @param position   选中的position
         * @param selectItem 选中的 item data
         */
        void onSelected(int position, String selectItem);
    }
}
