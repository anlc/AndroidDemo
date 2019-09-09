package com.android.demo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.demo.R;

/**
 * Created by Administrator on 2018/4/16.
 */

public class ListItemView extends FrameLayout {

    private TextView keyView;
    private TextView valueView;
    private ImageView arrowView;
    private ViewConfig config;

    public ListItemView(@NonNull Context context) {
        this(context, null);
    }

    public ListItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        config = new ViewConfig();
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_list_view, this, true);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ListItemView);
        config.keyText = array.getString(R.styleable.ListItemView_item_key);
        config.keyTextColor = array.getColor(R.styleable.ListItemView_item_key_color, getResources().getColor(R.color.color_333333));
        config.keyTextSize = array.getInt(R.styleable.ListItemView_item_key_size, 14);
        config.valueText = array.getString(R.styleable.ListItemView_item_value);
        config.valueTextColor = array.getColor(R.styleable.ListItemView_item_value_color, getResources().getColor(R.color.color_666666));
        config.valueTextSize = array.getInt(R.styleable.ListItemView_item_value_size, 12);
        config.arrowImgRes = array.getDrawable(R.styleable.ListItemView_item_right_img);
        config.isShowArrow = array.getBoolean(R.styleable.ListItemView_item_show_arrow, false);
        config.isShowBottomLine = array.getBoolean(R.styleable.ListItemView_item_show_bottom_line, false);
        array.recycle();

        keyView = rootView.findViewById(R.id.item_key);
        valueView = rootView.findViewById(R.id.item_value);
        arrowView = rootView.findViewById(R.id.item_arrow);

        keyView.setText(config.keyText);
        keyView.setTextSize(config.keyTextSize);
        keyView.setTextColor(config.keyTextColor);

        valueView.setText(config.valueText);
        valueView.setTextSize(config.valueTextSize);
        valueView.setTextColor(config.valueTextColor);

        arrowView.setVisibility(config.isShowArrow ? VISIBLE : GONE);
        if (config.arrowImgRes != null) {
            arrowView.setImageDrawable(config.arrowImgRes);
            arrowView.setVisibility(VISIBLE);
        }
    }

    @Override
    protected void onLayout(boolean changed, int pl, int pt, int pr, int pb) {
        View keyView = getChildAt(0);
        View valueView = getChildAt(1);
        View arrowView = getChildAt(2);

        int left = getPaddingLeft();
        int right = getMeasuredWidth() - getPaddingRight();

        int centerY = getHeight() / 2;
        int keyTop = centerY - keyView.getMeasuredHeight() / 2;
        keyView.layout(left, keyTop, left + keyView.getMeasuredWidth(), keyTop + keyView.getMeasuredHeight());
        if (arrowView.getVisibility() != GONE) {
            int arrowTop = centerY - arrowView.getMeasuredHeight() / 2;
            arrowView.layout(right - arrowView.getMeasuredWidth(), arrowTop, right, arrowTop + arrowView.getMeasuredHeight());
            right -= arrowView.getMeasuredWidth();
        }
        int valueTop = centerY - valueView.getMeasuredHeight() / 2;
        valueView.layout(right - valueView.getMeasuredWidth(), valueTop, right, valueTop + valueView.getMeasuredHeight());

        Log.e("tag", right - valueView.getMeasuredWidth() + ":" + valueTop + ":" + right + ":" + valueTop + valueView.getMeasuredHeight());
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        setMeasuredDimension(widthMeasureSpec, heightMode == MeasureSpec.EXACTLY ? MeasureUtil.dp(getContext(), 42) : heightSize);
////        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }

    private class ViewConfig {
        String keyText;
        int keyTextSize;
        int keyTextColor;
        String valueText;
        int valueTextSize;
        int valueTextColor;
        Drawable arrowImgRes;
        boolean isShowArrow;
        boolean isShowBottomLine;
    }
}
