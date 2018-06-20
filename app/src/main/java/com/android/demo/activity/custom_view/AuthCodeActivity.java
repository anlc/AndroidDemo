package com.android.demo.activity.custom_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.demo.R;
import com.android.demo.listener.SoftKeyBoardListener;
import com.android.demo.widget.EditPhoneNumView;

/**
 * Created by Administrator on 2018/4/4.
 */

public class AuthCodeActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_code);
    }
}
