package com.android.demo.adapter;


import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 */

public abstract class BaseListAdapter<T> extends android.widget.BaseAdapter {

    protected Context context;
    protected List<T> data;
    protected LayoutInflater inflater;

    public BaseListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        if (this.data == null) {
            this.data = data;
        } else {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addData(T data) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data.add(data);
        }
        notifyDataSetChanged();
    }
}
