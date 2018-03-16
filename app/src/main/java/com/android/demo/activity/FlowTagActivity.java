package com.android.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.android.demo.R;
import com.android.demo.utils.CheckUtil;
import com.android.demo.utils.EditTextUtil;
import com.android.demo.widget.FlowLayout;

/**
 * Created by Administrator on 2018/2/9.
 */

public class FlowTagActivity extends AppCompatActivity {

    FlowLayout flowLayout;
    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_tag);

        flowLayout = findViewById(R.id.flow_layout);
        editText = findViewById(R.id.edit_view);
        flowLayout.addView("item 1", "test 1");

        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = EditTextUtil.getText(editText);
                if (CheckUtil.isNull(tag, "请输入信息")) return;
                flowLayout.addView(tag);
                editText.setText("");
            }
        });
    }
}
