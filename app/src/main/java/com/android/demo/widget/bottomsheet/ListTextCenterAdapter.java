package com.android.demo.widget.bottomsheet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.demo.R;
import com.android.demo.listener.OnItemClickListener;
import com.android.demo.utils.MeasureUtil;

/**
 * Created by n on 2017/10/27.
 */

public class ListTextCenterAdapter extends RecyclerView.Adapter<ListTextCenterAdapter.Holder> {

    private Context context;
    private String[] data;
    private OnItemClickListener onItemClickListener;
    private BottomSheetDialog dialog;

    public ListTextCenterAdapter(Context context, String[] data,
                                 OnItemClickListener onItemClickListener, BottomSheetDialog dialog) {
        this.context = context;
        this.data = data;
        this.onItemClickListener = onItemClickListener;
        this.dialog = dialog;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(context);
        textView.setTextSize(14);
        textView.setHeight(MeasureUtil.dp(42));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(context.getResources().getColor(R.color.color_333333));
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new Holder(textView);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        ((TextView) holder.itemView).setText(data[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
                dialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }
}
