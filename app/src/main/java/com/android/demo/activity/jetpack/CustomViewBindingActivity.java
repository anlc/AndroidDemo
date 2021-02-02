package com.android.demo.activity.jetpack;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.demo.R;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2021/1/26
 */
public class CustomViewBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_custom_view_binding);
    }

    public static class CustomView extends androidx.appcompat.widget.AppCompatEditText {

        public CustomView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public void setContentMsg(String string) {
            setText(string);
        }
    }
}
