package com.android.demo.activity.animation

import android.animation.*
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.databinding.ActivityValueAnimationBinding

class ValueAnimationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activity = DataBindingUtil.setContentView<ViewDataBinding>(
            this,
            R.layout.activity_value_animation
        ) as ActivityValueAnimationBinding

        activity.translationX.setOnClickListener {
            ObjectAnimator
                .ofFloat(activity.animationView, "translationX", 0f, 100f)
                .apply {
                    duration = 400
                    start()
                }
        }

        val toLeft = ObjectAnimator
            .ofFloat(activity.animationView, "translationX", -100f)
            .apply { duration = 1500 }

        val toShow = ObjectAnimator
            .ofFloat(activity.animationView, "alpha", 0f, 1f)
            .apply { duration = 1500 }

        val toBack = ObjectAnimator
            .ofFloat(activity.animationView, "translationX", 0f)
            .apply { duration = 1500 }

        val toHide = ObjectAnimator
            .ofFloat(activity.animationView, "alpha", 1f, 0f)
            .apply { duration = 1500 }

        activity.translationXSet.setOnClickListener {
            AnimatorSet().apply {
                play(toHide.clone()).before(toLeft)
                play(toLeft).with(toShow.clone())
                play(toBack).with(toHide.clone())
                play(toLeft).before(toBack)
                play(toBack).before(toShow.clone())
                start()
            }
        }

        val scaleX =
            ObjectAnimator.ofFloat(activity.scaleView, "scaleX", 0.2f, 1f).apply { duration = 200 }
        val scaleY =
            ObjectAnimator.ofFloat(activity.scaleView, "scaleY", 0.2f, 1f).apply { duration = 200 }

        activity.scale.setOnClickListener {
            activity.scaleView.visibility = View.VISIBLE
            activity.scaleView.pivotX = 0f
            activity.scaleView.pivotY = 0f
            AnimatorSet().apply {
                play(scaleX).with(scaleY)
                interpolator = AccelerateInterpolator()
                start()
            }
        }

        activity.keyframe.setOnClickListener {
            val k1 = Keyframe.ofFloat(0f, 0f)
            val k2 = Keyframe.ofFloat(0.5f, 360f)
            val k3 = Keyframe.ofFloat(1f, 0f)
            val keyframe = PropertyValuesHolder.ofKeyframe("rotation", k1, k2, k3)
            ObjectAnimator.ofPropertyValuesHolder(activity.animationView, keyframe).apply {
                duration = 3000
                start()
            }
        }

        activity.propertyAnimation.setOnClickListener {
            val x = PropertyValuesHolder.ofFloat("x", 50f)
            val y = PropertyValuesHolder.ofFloat("y", 100f)
            ObjectAnimator.ofPropertyValuesHolder(activity.animationView, x, y).apply {
                duration = 2000
                start()
            }
        }

        activity.viewPropertyAnimation.setOnClickListener {
            activity.animationView.animate().x(50f).y(100f).duration = 2000
        }

        activity.loadAnimationXml.setOnClickListener {
            (AnimatorInflater.loadAnimator(baseContext, R.animator.property_animator) as AnimatorSet).apply {
                setTarget(activity.animationView)
                start()
            }
        }
    }
}