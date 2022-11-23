package com.android.demo.activity.animation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.databinding.ActivityPathInterpolatorBinding

class PathInterpolatorActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activity = DataBindingUtil.setContentView<ViewDataBinding>(
            this,
            R.layout.activity_path_interpolator
        ) as ActivityPathInterpolatorBinding

        activity.animationView.setOnClickListener {
        }
    }
}