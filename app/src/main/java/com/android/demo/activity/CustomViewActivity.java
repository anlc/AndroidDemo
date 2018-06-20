package com.android.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.android.demo.base.Constants;
import com.android.demo.base.ManifestActivity;

/**
 * Created by Administrator on 2018/6/20.
 */

public class CustomViewActivity extends ManifestActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = new ListView(this);
        setContentView(listView);

        setAdapter(listView, Constants.ACTION_CUSTOM_VIEW);
    }
}
