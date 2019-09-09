package com.android.demo.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.demo.R;

/**
 * Created by Administrator on 2018/2/8.
 */

public class FlowLayout extends ViewGroup {

    public FlowLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int height = getPaddingTop() + getPaddingBottom();
        int lineWidth = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int childHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            int childWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            if (i == 0) {
                height += childHeight;
            }
            if (childWidth + lineWidth > width) {
                lineWidth = 0;
                height += childHeight;
            }
            lineWidth += childWidth;
        }
        setMeasuredDimension(widthMeasureSpec, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int lineWidth = 0;
        int lastLineHeight = 0;

        int left = getPaddingLeft(), top = getPaddingTop(), right = 0, bottom = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int childHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            int childWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            if (lineWidth + childWidth > width) {
                lineWidth = 0;
                left = getPaddingLeft();
                top += lastLineHeight;
            }
            lastLineHeight = childHeight;
            lineWidth += childWidth;
            left = left + params.leftMargin;
            right = left + childWidth - params.rightMargin;
            top = top + params.topMargin;
            bottom = top + childHeight - params.bottomMargin;
            child.layout(left, top, right, bottom);
            left += childWidth;
        }
    }

    public void addView(String... tag) {
        if (tag == null) {
            return;
        }
        for (int i = 0; i < tag.length; i++) {
            if (!TextUtils.isEmpty(tag[i])) {
                addView(generateItem(tag[i]));
            }
        }
    }

    private TextView generateItem(String string) {
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.parseColor("#cccccc"));
        bg.setCornerRadius(dp(3));
        TextView textView = new TextView(getContext());
        textView.setTextSize(14);
        textView.setText(string);
        textView.setTextColor(Color.BLACK);
        textView.setBackgroundResource(R.drawable.tag_circle_border_bg);
        textView.setPadding(dp(8), dp(4), dp(8), dp(4));
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.rightMargin = dp(8);
        params.bottomMargin = dp(4);
        textView.setLayoutParams(params);
        return textView;
    }

    public int dp(int size) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, getContext().getResources().getDisplayMetrics());
    }
}