package com.task.remote;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2020/11/26
 */
public class LaunchModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText(getClass().getName());
        textView.setTextSize(30);
        setContentView(textView);
    }
}
