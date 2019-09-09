package com.android.demo.widget.bottomsheet;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.demo.listener.OnItemClickListener;


/**
 * Created by chenshuang on 2017/8/21.
 */

public class BottomSheetList {

    private BottomSheetDialog dialog;
    private ListTextCenterAdapter adapter;

    public BottomSheetList(Context context, String[] data, OnItemClickListener onItemClickListener) {
        RecyclerView recyclerView = new RecyclerView(context);
        dialog = new BottomSheetDialog(context);
        dialog.setContentView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ListTextCenterAdapter(context, data, onItemClickListener, dialog);
        recyclerView.setAdapter(adapter);
    }

    public void show() {
        if (dialog != null)
            dialog.show();
    }


    public BottomSheetList show1() {
        if (dialog != null)
            dialog.show();
        return this;
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }
}
