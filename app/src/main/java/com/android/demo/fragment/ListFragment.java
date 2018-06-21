package com.android.demo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.demo.R;
import com.android.demo.adapter.BaseAdapter;
import com.android.demo.adapter.SimpleAdapter;
import com.android.demo.base.BaseFragment;

import java.util.Collections;

/**
 * Created by Administrator on 2018/6/21.
 */

public class ListFragment extends BaseFragment{
    @Override
    protected int contentViewResId() {
        return R.layout.fragment_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BaseAdapter adapter = new SimpleAdapter(getContext());
        adapter.setData(Collections.nCopies(30, "item"));
        recyclerView.setAdapter(adapter);
    }
}
