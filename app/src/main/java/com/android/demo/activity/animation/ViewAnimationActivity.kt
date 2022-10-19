package com.android.demo.activity.animation

import android.os.Bundle
import android.view.animation.*
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.databinding.ActivityViewAnimationBinding

class ViewAnimationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = DataBindingUtil.setContentView<ViewDataBinding>(
            this,
            R.layout.activity_view_animation
        ) as ActivityViewAnimationBinding

        activity.start.setOnClickListener {
            val set = AnimationSet(true)

            if (activity.radioScale.isChecked) {
                val animation = ScaleAnimation(0f, 1f, 0f, 1f, 0f, 0f)
                animation.duration = activity.scaleDurationValue.getValue()!!.toLong()
                set.addAnimation(animation)
            }
            if (activity.radioAlpha.isChecked) {
                val animation = AlphaAnimation(0f, 1f)
                animation.duration = activity.alphaDurationValue.getValue()!!.toLong()
                set.addAnimation(animation)
            }
            if (activity.radioRotate.isChecked) {
                val animation = RotateAnimation(
                    0f,
                    270f,
                    activity.animationView.width / 2f,
                    activity.animationView.height / 2f
                )
                animation.duration = activity.rotateDurationValue.getValue()!!.toLong()
                set.addAnimation(animation)
            }
            if (activity.radioTranslate.isChecked) {
                val animation = TranslateAnimation(
                    0f, activity.animationView.width.toFloat(),
                    0f, activity.animationView.height.toFloat()
                )
                animation.duration = activity.translateDurationValue.getValue()!!.toLong()
                set.addAnimation(animation)
            }
            activity.animationView.startAnimation(set)
        }
    }
}