package com.android.demo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.demo.R;
import com.android.demo.utils.MeasureUtil;

/**
 * Created by Administrator on 2018/3/5.
 */

public class TabLayout extends HorizontalScrollView {

    //设置底部的指示条宽度是和字体一样宽，还是和view一样宽
    public static final int MATCH_PARENT = 1;
    public static final int WRAP_CONTENT = 0;

    private ViewPager.OnPageChangeListener onPageChangeListener;
    private final TabStrip tabStrip;
    private ViewPager viewPager;

    private int titleOffset;
    private float density;
    private int titleColor;//设置标题颜色

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        density = getResources().getDisplayMetrics().density;

        setHorizontalScrollBarEnabled(false);
        setFillViewport(true);

        titleOffset = (int) (24 * density);

        tabStrip = new TabStrip(context, attrs);
        addView(tabStrip, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    public void setViewPager(ViewPager viewPager) {
        tabStrip.removeAllViews();

        this.viewPager = viewPager;
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(new InternalViewPagerListener());
            populateTabStrip();
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    private void populateTabStrip() {
        final PagerAdapter adapter = viewPager.getAdapter();
        final OnClickListener tabClickListener = new TabClickListener();
        for (int i = 0; i < adapter.getCount(); i++) {
            TabItem tabTitleView = new TabItem(getContext());
            tabTitleView.setText(adapter.getPageTitle(i));
            tabStrip.addView(tabTitleView);
            if (i == viewPager.getCurrentItem()) {
                tabTitleView.setSelected(true);
            }
            tabTitleView.setOnClickListener(tabClickListener);
        }
    }

    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {

        private int scrollState;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int tabStripChildCount = tabStrip.getChildCount();
            if ((tabStripChildCount == 0) || (position < 0) || (position >= tabStripChildCount)) {
                return;
            }
            tabStrip.onViewPagerPageChanged(position, positionOffset);
            View selectTitle = tabStrip.getChildAt(position);
            int extraOffset = selectTitle != null ? (int) (positionOffset * selectTitle.getWidth()) : 0;
            scrollToTab(position, extraOffset);

            if (onPageChangeListener != null) {
                onPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (scrollState == ViewPager.SCROLL_STATE_IDLE) {
                tabStrip.onViewPagerPageChanged(position, 0f);
                scrollToTab(position, 0);
            }
            for (int i = 0; i < tabStrip.getChildCount(); i++) {
                tabStrip.getChildAt(i).setSelected(position == i);
            }
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageSelected(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            scrollState = state;
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageScrollStateChanged(state);
            }
        }
    }

    private void scrollToTab(int tabIndex, int positionOffset) {
        final int childCount = tabStrip.getChildCount();
        if (childCount == 0 || tabIndex < 0 || tabIndex >= childCount) {
            return;
        }

        View selectTab = tabStrip.getChildAt(tabIndex);
        if (selectTab != null) {
            int scrollX = selectTab.getLeft() + positionOffset;
            if (tabIndex > 0 || positionOffset > 0) {
                scrollX -= titleOffset;
            }
            scrollTo(scrollX, 0);
        }
    }

    private class TabClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < tabStrip.getChildCount(); i++) {
                if (v == tabStrip.getChildAt(i)) {
                    viewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    }

    private class TabStrip extends LinearLayout {

        private final Paint borderPaint;
        private int borderHeight;

        private int selectPosition;
        private float selectOffset;

        private Bitmap indicateLine;
        private int indicateColor;
        private Rect indicateRect;
        private RectF dstRect;
        private int indicateWidth;

        public TabStrip(Context context) {
            this(context, null);
        }

        public TabStrip(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            setWillNotDraw(false);

            borderPaint = new Paint();
            dstRect = new RectF();
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TabLayout);
            indicateColor = array.getColor(R.styleable.TabLayout_line_indicate_color, -1);
            indicateWidth = array.getInt(R.styleable.TabLayout_line_indicate_width, WRAP_CONTENT);
            titleColor = array.getColor(R.styleable.TabLayout_tab_title_color, Color.BLACK);
            if (indicateColor != -1) {
                borderPaint.setStyle(Paint.Style.FILL);
                borderPaint.setColor(indicateColor);
                borderHeight = MeasureUtil.dp(2);
            }
            Drawable drawable = array.getDrawable(R.styleable.TabLayout_line_indicate_drawable);
            if (drawable != null) {
                indicateLine = ((BitmapDrawable) drawable).getBitmap();
                indicateRect = new Rect(0, 0, indicateLine.getWidth(), indicateLine.getHeight());
                borderHeight = indicateLine.getHeight();
            }
        }

        void onViewPagerPageChanged(int position, float positionOffset) {
            selectPosition = position;
            selectOffset = positionOffset;
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            final int height = getHeight();
            final int childCount = getChildCount();

            if (childCount > 0) {
                View selectView = getChildAt(selectPosition);
                int left = selectView.getLeft();
                int right = selectView.getRight();
                if (indicateWidth == WRAP_CONTENT) {
                    left = selectView.getLeft() + ((TabItem) selectView).getTextLeft();
                    right = selectView.getRight() - ((TabItem) selectView).getTextLeft();
                }

                if (selectOffset > 0f && selectPosition < (getChildCount() - 1)) {
                    View nextView = getChildAt(selectPosition + 1);
                    if (indicateWidth == WRAP_CONTENT) {
                        int l = nextView.getLeft() + ((TabItem) nextView).getTextLeft();
                        int r = selectView.getRight() + ((TabItem) nextView).getTextRight();
                        left = (int) (selectOffset * l + (1.0f - selectOffset) * left);
                        right = (int) (selectOffset * r + (1.0f - selectOffset) * right);
                    } else {
                        left = (int) (selectOffset * nextView.getLeft() + (1.0f - selectOffset) * left);
                        right = (int) (selectOffset * nextView.getRight() + (1.0f - selectOffset) * right);
                    }
                }

                dstRect.left = left;
                dstRect.right = right;
                dstRect.top = height - borderHeight;
                dstRect.bottom = height;
                if (indicateColor != -1) {
                    canvas.drawRect(dstRect, borderPaint);
                } else if (indicateLine != null) {
                    canvas.drawBitmap(indicateLine, indicateRect, dstRect, borderPaint);
                }
            }
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int screenWidth = getMeasuredWidth();
            int width = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                width += child.getMeasuredWidth();
            }
            if (width < screenWidth) {
                updateChildWidth();
            }
        }

        private void updateChildWidth() {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                child.setMinimumWidth(getMeasuredWidth() / getChildCount());
            }
        }
    }

    class TabItem extends FrameLayout {

        TextView textView;

        public TabItem(@NonNull Context context) {
            super(context);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            textView = createDefaultTabView();
            addView(textView, params);
        }

        protected TextView createDefaultTabView() {
            TextView textView = new TextView(getContext());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setTextColor(titleColor);
            int tap = (int) (12 * density);
            textView.setPadding(0, tap, 0, tap);
            return textView;
        }

        public void setText(CharSequence text) {
            textView.setText(text);
        }

        public int getTextLeft() {
            return getChildAt(0).getLeft();
        }

        public int getTextRight() {
            return getChildAt(0).getRight();
        }
    }
}
