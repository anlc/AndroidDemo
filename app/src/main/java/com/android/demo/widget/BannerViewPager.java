package com.android.demo.widget;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.demo.R;
import com.android.demo.utils.MeasureUtil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/2/28.
 */

public class BannerViewPager extends FrameLayout {

    private ViewPager viewPager;
    private LinearLayout dotLayout;
    private List<String> data;
    private Timer timer;

    public BannerViewPager(@NonNull Context context) {
        this(context, null);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        viewPager = new ViewPager(context);
        addView(viewPager);
        dotLayout = new LinearLayout(context);
        dotLayout.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        layoutParams.bottomMargin = MeasureUtil.dp(context, 16);
        layoutParams.rightMargin = MeasureUtil.dp(context, 16);
        addView(dotLayout, layoutParams);
        setBackgroundColor(Color.WHITE);

//        context.getApplicationContext().registerComponentCallbacks(new ComponentCallbacks2() {
//            @Override
//            public void onTrimMemory(int level) {
//
//            }
//
//            @Override
//            public void onConfigurationChanged(Configuration newConfig) {
//
//            }
//
//            @Override
//            public void onLowMemory() {
//
//            }
//        });
    }

    public void setImages(List<String> data) {
        if (data == null) return;
        this.data = data;

        for (int i = 0; i < data.size(); i++) {
            if (data.size() == 1) {
                break;
            }
            View dotView = new View(getContext());
            int width = MeasureUtil.dp(getContext(), 5);
            LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(width, width);
            dotView.setBackgroundResource(R.drawable.selector_dot_bg);
            dotParams.leftMargin = MeasureUtil.dp(getContext(), 5);
            dotLayout.addView(dotView, dotParams);
            if (i == 0) {
                dotView.setSelected(true);
            }
        }
        viewPager.setAdapter(new BannerViewAdapter());
        viewPager.setScrollBarFadeDuration(3000);
        viewPager.addOnPageChangeListener(new PageChangeListener());
        startTimer();
    }

    class PageChangeListener implements ViewPager.OnPageChangeListener {

        int oldPosition = 0;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            dotLayout.getChildAt(position).setSelected(true);
            dotLayout.getChildAt(oldPosition).setSelected(false);
            oldPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class BannerViewAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(R.mipmap.ic_launcher);
            container.addView(imageView);
            return imageView;
        }
    }

    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                post(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewPager.getCurrentItem();
                        if (position >= data.size() - 1) {
                            position = 0;
                        } else {
                            position++;
                        }
                        viewPager.setCurrentItem(position);
                    }
                });
            }
        }, 3000, 3000);
    }

    public void release() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        release();
    }
}
