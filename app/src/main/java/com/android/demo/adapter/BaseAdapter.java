package com.android.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by n on 2017/11/26.
 */

public abstract class BaseAdapter<T, Holder extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<Holder> {

    protected Context context;
    protected List<T> data;
    private int layoutRes;

    public BaseAdapter(Context context, int layoutRes) {
        this.context = context;
        this.layoutRes = layoutRes;
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutRes, parent, false);
        return newHolder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        onBind(holder, position);
    }

    protected abstract Holder newHolder(View view);

    protected abstract void onBind(Holder holder, int position);

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }
}
