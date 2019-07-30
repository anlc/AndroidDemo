package com.android.demo.activity.custom_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.demo.R;
import com.android.demo.utils.Utils;
import com.android.demo.widget.MultiSquareEditView;

/**
 * Created by Administrator on 2018/4/4.
 */

public class AuthCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_code);

        MultiSquareEditView authView = findViewById(R.id.ac_edit_auth_code);
        authView.setCursorVisible(true);
        authView.setOnInputCompleteListener(new MultiSquareEditView.InputCompleteListener() {
            @Override
            public void inputComplete(String inputText) {
                Utils.toast("输入验证码：" + inputText);
            }
        });
    }
}
