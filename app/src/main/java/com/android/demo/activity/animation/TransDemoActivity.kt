package com.android.demo.activity.animation

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.databinding.ActivityTransDemoBinding

class TransDemoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = DataBindingUtil.setContentView<ViewDataBinding>(
            this,
            R.layout.activity_trans_demo
        ) as ActivityTransDemoBinding

        activity.openSetting.setOnClickListener {
            activity.container.visibility = View.VISIBLE
            val rect = Rect()
            val globalVisibleRect = activity.container.getGlobalVisibleRect(rect)

            val visibleRect = Rect()
            Log.i(Companion.TAG, "scale animation: $globalVisibleRect, visibleRect: $visibleRect")
            val left = ValueAnimator.ofFloat(rect.right.toFloat(), 0f).apply {
                addUpdateListener {
                    val toInt = (it.animatedValue as Float).toInt()
                    visibleRect.left = toInt
                    activity.container.clipBounds = visibleRect
                }
            }

            val top = ValueAnimator.ofFloat(rect.top.toFloat(), 0f).apply {
                addUpdateListener {
                    val toInt = (it.animatedValue as Float).toInt()
                    visibleRect.top = toInt
                    activity.container.clipBounds = visibleRect
                }
            }


            val right = ValueAnimator.ofFloat(rect.right.toFloat() - 100, rect.right.toFloat()).apply {
                addUpdateListener {
                    val toInt = (it.animatedValue as Float).toInt()
                    visibleRect.right = toInt
                    activity.container.clipBounds = visibleRect
                }
            }

            val bottom = ValueAnimator.ofFloat(0f, rect.bottom.toFloat()).apply {
                addUpdateListener {
                    val toInt = (it.animatedValue as Float).toInt()
                    visibleRect.bottom = toInt
                    activity.container.clipBounds = visibleRect
                }
            }

            AnimatorSet().apply {
                duration = 500
                interpolator = AccelerateDecelerateInterpolator()
                play(left).with(bottom).with(top).with(right)
                start()
            }
        }
    }

    companion object {
        private const val TAG = "TransDemoActivity"
    }
}