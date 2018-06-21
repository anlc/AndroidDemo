package com.android.demo.activity.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.demo.R;
import com.android.demo.adapter.BaseAdapter;
import com.android.demo.adapter.FragmentAdapter;
import com.android.demo.adapter.SimpleAdapter;
import com.android.demo.base.BaseActivity;
import com.android.demo.fragment.ListFragment;

import java.util.ArrayList;
import java.util.Collections;
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
