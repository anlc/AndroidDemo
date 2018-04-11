package com.android.demo.listener;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Administrator on 2018/4/11.
 */

public class SoftKeyBoardListener {

    public static SoftKeyBoardListener create() {
        return new SoftKeyBoardListener();
    }

    public interface Listener {
        void softKeyBoardVisibleChanged(boolean isShowing);
    }

    public void listener(final Activity activity, EditText view, final Listener listener) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.softKeyBoardVisibleChanged(isSoftKeyBoardShowing(activity));
                }
            }
        });
    }

    /**
     * 软键盘是否正在显示
     */
    public static boolean isSoftKeyBoardShowing(Activity activity) {
        Rect windowRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(windowRect);
        int screenHeight = activity.getWindow().getDecorView().getHeight();
        return windowRect.bottom - screenHeight != 0;
    }
}
