package com.android.demo.activity.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.databinding.ActivityShowAndHideBinding
import kotlin.math.hypot

class ShowAndHideAnimationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = DataBindingUtil.setContentView<ViewDataBinding>(
            this,
            R.layout.activity_show_and_hide
        ) as ActivityShowAndHideBinding

        // fade animation
        activity.content.visibility = View.INVISIBLE
        activity.fade.setOnClickListener {
            if (activity.content.visibility == View.INVISIBLE) {
                fade(activity.content, activity.loadingSpinner)
            } else {
                fade(activity.loadingSpinner, activity.content)
            }
        }

        // circular reveal animation
        activity.circularReveal.setOnClickListener {
            if (activity.animationView.visibility == View.VISIBLE) {
                hide(activity.animationView)
            } else {
                show(activity.animationView)
            }
        }
    }

    private fun fade(toShowView: View, toHideView: View) {
        toShowView.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate().alpha(1f)
                .setDuration(
                    resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
                )
                .setListener(null)
        }
        toHideView.animate()
            .alpha(0f)
            .setDuration(resources.getInteger(android.R.integer.config_shortAnimTime).toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    toHideView.visibility = View.INVISIBLE
                }
            })
    }

    private fun show(view: View) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            view.visibility = View.VISIBLE
            return
        }

        val cx = view.width / 2
        val cy = view.height / 2

        val finalRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()
        val animator = ViewAnimationUtils.createCircularReveal(
            view,
            cx,
            cy,
            0f,
            finalRadius
        )
        view.visibility = View.VISIBLE
        animator.start()
    }

    private fun hide(view: View) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            view.visibility = View.INVISIBLE
            return
        }
        val cx = view.width / 2
        val cy = view.height / 2

        val finalRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()
        val animator = ViewAnimationUtils.createCircularReveal(
            view,
            cx,
            cy,
            finalRadius,
            0f
        )

        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                view.visibility = View.INVISIBLE
            }
        })
        animator.start()
    }
}