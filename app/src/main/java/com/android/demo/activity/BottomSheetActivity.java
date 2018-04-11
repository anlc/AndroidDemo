package com.android.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.demo.R;
import com.android.demo.listener.OnItemClickListener;
import com.android.demo.widget.bottomsheet.BottomSheet;
import com.android.demo.widget.bottomsheet.BottomSheetList;

/**
 * Created by Administrator on 2018/4/11.
 */

public class BottomSheetActivity extends AppCompatActivity implements View.OnClickListener {

    private BottomSheet bottomSheet;

    private String[] languages;
    private TextView ac_select_language;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        init();
    }

    private void init() {
        ac_select_language = findViewById(R.id.ac_select_language);
        ac_select_language.setOnClickListener(this);
        findViewById(R.id.ac_show_bottom).setOnClickListener(this);
        languages = new String[]{"Java", "C++", "C", "JS", "Python", "Kotlin"};
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ac_select_language:
                showLanguageDialog();
                break;
            case R.id.ac_show_bottom:
                showBottomSelect();
                break;
            case R.id.bottom_content_layout:
                bottomSheet.dismiss();
                break;
        }
    }

    private void showLanguageDialog() {
        new BottomSheetList(this, languages, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ac_select_language.setText(languages[position]);
            }
        }).show();
    }

    private void showBottomSelect() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_bottom_layout, null);
        view.findViewById(R.id.bottom_content_layout).setOnClickListener(this);
        bottomSheet = new BottomSheet.Builder(this).contentView(view).build();
        bottomSheet.show();
    }
}
