package com.android.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ListView;

import com.android.demo.R;
import com.android.demo.base.ManifestActivity;

public class MainActivity extends ManifestActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.ac_list);
        setAdapter(listView, Intent.ACTION_MAIN);
    }
}
