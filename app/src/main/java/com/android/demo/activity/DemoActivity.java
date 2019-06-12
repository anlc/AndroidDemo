package com.android.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.android.demo.base.Constants;
import com.android.demo.base.ManifestActivity;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019/1/26
 */
public class DemoActivity extends ManifestActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = new ListView(this);
        setContentView(listView);

        setAdapter(listView, Constants.ACTION_APP_DEMO);
    }
}
