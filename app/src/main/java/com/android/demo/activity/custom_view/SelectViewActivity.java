package com.android.demo.activity.custom_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.demo.R;
import com.android.demo.widget.selectview.SelectView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */

public class SelectViewActivity extends AppCompatActivity {

    SelectView selectView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_view);

        selectView = findViewById(R.id.select_view);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("item " + i);
        }
        selectView.setListData(data);
    }
}
