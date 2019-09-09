package com.android.demo.activity.demo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

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
