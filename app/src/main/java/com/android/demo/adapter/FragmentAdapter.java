package com.android.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/6/21.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private List<? extends Fragment> fragments;
    private String[] tabTitle;

    public FragmentAdapter(FragmentManager fm, List<? extends Fragment> fragments, String[] tabTitle) {
        super(fm);
        this.fragments = fragments;
        this.tabTitle = tabTitle;
    }

    public void setTabTitle(String[] tabTitle) {
        this.tabTitle = tabTitle;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}