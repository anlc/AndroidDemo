package com.android.demo.activity.custom_view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.demo.R;
import com.android.demo.base.BaseActivity;
import com.android.demo.widget.selectview.SelectItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2020/5/19
 */
public class SelectItemViewActivity extends BaseActivity {

    SelectItemView selectItemView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_item_view);

        selectItemView = findViewById(R.id.select_item_view);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("item " + i);
        }
        selectItemView.setListData(data);

        selectItemView.setOnSelectChangedListener(new SelectItemView.OnSelectChangedListener() {
            @Override
            public void onSelected(int position, String selectItem) {
                Toast.makeText(getBaseContext(), selectItem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
