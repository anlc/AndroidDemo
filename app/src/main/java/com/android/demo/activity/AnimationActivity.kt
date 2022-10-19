package com.android.demo.activity

import android.os.Bundle
import com.android.demo.base.Constants
import com.android.demo.base.ManifestActivity

class AnimationActivity : ManifestActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentAndAction(Constants.ACTION_ANIMATION)
    }
}