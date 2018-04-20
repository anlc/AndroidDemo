package com.android.demo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.android.demo.R;

/**
 * Created by Administrator on 2018/2/9.
 */

public class EditTextUtil {

    public static String getText(EditText editText) {
        if (editText == null) {
            return "";
        }
        return editText.getText().toString().trim();
    }

    public static String getText(TextView editText) {
        if (editText == null) {
            return "";
        }
        return editText.getText().toString().trim();
    }

    public static void setText(EditText editText, String string) {
        if (editText == null) {
            return;
        }
        if (string == null) {
            editText.setText("");
            return;
        }
        editText.setText(string);
        editText.setSelection(string.length());
    }

    public static void setText(Context context, TextView textView, String str, int hintRes) {
        if (textView == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            textView.setText(context.getResources().getString(hintRes));
            textView.setTextColor(context.getResources().getColor(R.color.color_999999));
        } else {
            textView.setText(str);
            textView.setTextColor(context.getResources().getColor(R.color.color_333333));
        }
    }
}
