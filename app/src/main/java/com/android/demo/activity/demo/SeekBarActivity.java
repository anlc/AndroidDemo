package com.android.demo.activity.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.android.demo.R;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019/1/26
 */
public class SeekBarActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_seek_bar);
    }
}
