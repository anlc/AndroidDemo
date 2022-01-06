package com.android.demo.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.android.demo.base.Constants;
import com.android.demo.base.ManifestActivity;

/**
 * <p>
 *
 * @author anlc
 * @date 2021/1/5
 */
public class JetpackActivity extends ManifestActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = new ListView(this);
        setContentView(listView);

        setAdapter(listView, Constants.ACTION_JETPACK);
    }
}

