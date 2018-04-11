package com.android.demo.widget.bottomsheet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by n on 2017/10/26.
 */

public class BottomSheet {
    private BottomSheetDialog dialog;

    private BottomSheet(Builder builder) {
        dialog = new BottomSheetDialog(builder.context);
        View view = builder.contentView;
        if (view == null) {
            view = LayoutInflater.from(builder.context).inflate(builder.contentViewRes, null);
        }
        dialog.setContentView(view);
    }

    public void show() {
        if (dialog != null)
            dialog.show();
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    public static class Builder {

        private Context context;
        private View contentView;
        private int contentViewRes;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder contentView(View contentView) {
            this.contentView = contentView;
            return this;
        }

        public Builder contentViewRes(int contentViewRes) {
            this.contentViewRes = contentViewRes;
            return this;
        }

        public BottomSheet build() {
            return new BottomSheet(this);
        }
    }
}
