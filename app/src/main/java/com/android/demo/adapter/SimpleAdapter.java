package com.android.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.demo.R;

/**
 * Created by Administrator on 2018/6/21.
 */

public class SimpleAdapter extends BaseAdapter<String, RecyclerView.ViewHolder> {

    public SimpleAdapter(Context context) {
        super(context, R.layout.item_simple_text_view);
    }

    @Override
    protected RecyclerView.ViewHolder newHolder(View view, int viewType) {
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    protected void onBind(RecyclerView.ViewHolder holder, int position) {
        ((TextView) holder.itemView).setText(data.get(position));
    }
}
