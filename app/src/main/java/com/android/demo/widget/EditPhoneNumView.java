package com.android.demo.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Administrator on 2018/4/10.
 */

public class EditPhoneNumView  extends EditText {

    public EditPhoneNumView(Context context) {
        super(context);
        init();
    }

    public EditPhoneNumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditPhoneNumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        addTextChangedListener(new TextWatcher() {
            int beforeLength = 0;
            boolean isChange = true;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeLength = s.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() < beforeLength) {//删除文字, 不做处理
                    return;
                }

                String temp = new String(s.toString());
                if (temp.contains(" ") && temp.indexOf(" ") != 3 && temp.indexOf(" ") != 8 && isChange) {
                    isChange = false;
                    s.replace(0, s.length(), "");
                }

                //格式化粘贴的字符串
                if (temp.length() > 11) {
                    temp = temp.replace(" ", "");
                    if (temp.length() > 11) {
                        temp = temp.substring(0, 11);
                    }
                    StringBuilder builder = new StringBuilder(temp);
                    builder.insert(3, " ");
                    builder.insert(8, " ");
                    if (isChange) {
                        isChange = false;
                        s.replace(0, s.length(), builder);
                    }
                }

//                if (s.length() > beforeLength) {
//                    if (s.length() == 3) {
//                        s.append(" ");
//                    } else if (s.length() == 8) {
//                        s.append(" ");
//                    }
//                }

                if (s.length() > 3 && !String.valueOf(s.charAt(3)).equals(" ") && isChange) {
                    isChange = false;
                    s.insert(3, " ");
                }
                if (s.length() > 8 && !String.valueOf(s.charAt(8)).equals(" ") && isChange) {
                    isChange = false;
                    s.insert(8, " ");
                }
                if (beforeLength == 0 && s.length() == 11) {
                    s.insert(3, " ");
                    s.insert(8, " ");
                    setSelection(s.length());
                }

                //删除输入的空格
                if (s.toString().endsWith(" ") && s.length() != 4 && s.length() != 9 && isChange) {
                    isChange = false;
                    s.delete(s.length() - 1, s.length());
                }
                isChange = true;
            }
        });
    }

    public String getString() {
        return getText().toString().replace(" ", "");
    }

    //匹配大陆手机号码
    public boolean matchMainLandMobile() {
        String regex = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
        return getString().matches(regex);
    }
}
