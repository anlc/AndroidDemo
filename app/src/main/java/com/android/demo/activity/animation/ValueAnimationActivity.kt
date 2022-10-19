package com.android.demo.activity.animation

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.widget.TextView
import com.android.demo.base.BaseActivity

class ValueAnimationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView = TextView(this)
        textView.text = "value animation"

        setContentView(textView)

        ValueAnimator.ofFloat(0f, 100f).apply {
            duration = 400
            start()
        }

        ObjectAnimator.ofFloat(textView, "translationX", 100f).apply {
            duration = 400
            start()
        }
    }
}