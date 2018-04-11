package com.android.demo.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Administrator on 2018/4/10.
 */

public class EditPhoneNumView extends EditText {

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

    private void init(){
        addTextChangedListener(new TextWatcher() {
            int beforeLength = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeLength = s.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > beforeLength) {
                    if (s.length() == 3) {
                        s.append(" ");
                    } else if (s.length() == 8) {
                        s.append(" ");
                    }
                }
                if (beforeLength==0 && s.length() == 11) {
                    s.insert(3, " ");
                    s.insert(8, " ");
                    setSelection(s.length());
                }
            }
        });
    }

    public String getString(){
        return getText().toString().replace(" ", "");
    }
}
