package com.android.demo.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.android.demo.base.Constants;
import com.android.demo.base.ManifestActivity;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-06-12
 */
public class AnimationActivity extends ManifestActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentAndAction(Constants.ACTION_ANIMATION);
    }
}
