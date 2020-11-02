package com.android.demo.activity.design;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.demo.R;
import com.android.demo.adapter.FragmentAdapter;
import com.android.demo.base.BaseActivity;
import com.android.demo.fragment.ListFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/6/20.
 */

public class CoordinatorLayoutActivity extends BaseActivity {

    private final String[] tabs = new String[]{"tab1", "tab2", "tab3"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        ViewPager viewPager = findViewById(R.id.ac_view_pager);
        TabLayout tabLayout = findViewById(R.id.ac_tab_layout);

        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < tabs.length; i++) {
            fragmentList.add(new ListFragment());
        }
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentList, tabs));
        tabLayout.setupWithViewPager(viewPager);
    }


}
