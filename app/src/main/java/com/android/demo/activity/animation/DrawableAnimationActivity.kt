package com.android.demo.activity.animation

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.databinding.ActivityDrawableAnimationBinding

class DrawableAnimationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = DataBindingUtil.setContentView<ViewDataBinding>(
            this,
            R.layout.activity_drawable_animation
        ) as ActivityDrawableAnimationBinding

        activity.start.setOnClickListener {
            activity.animationView.setImageResource(R.drawable.animatorvectordrawable)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                (activity.animationView.drawable as AnimatedVectorDrawable).start()
            }
        }
    }
}