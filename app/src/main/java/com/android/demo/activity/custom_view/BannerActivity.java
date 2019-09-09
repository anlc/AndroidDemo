package com.android.demo.activity.custom_view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.demo.R;
import com.android.demo.widget.BannerViewPager;

import java.util.Collections;

/**
 * Created by Administrator on 2018/4/11.
 */

public class BannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        BannerViewPager banner = findViewById(R.id.ac_banner);
        banner.setImages(Collections.nCopies(5, new String()));
    }
}
