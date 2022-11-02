package com.android.demo.activity.animation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.transition.*
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.databinding.ActivitySceneMainBinding
import com.google.android.material.transition.Hold

class SceneMainActivity : BaseActivity() {

    private var startIndex = 5
    private var index = startIndex

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = DataBindingUtil.setContentView<ViewDataBinding>(
            this,
            R.layout.activity_scene_main
        ) as ActivitySceneMainBinding

        activity.start.setOnClickListener {
//            val textView = TextView(this).apply {
//                text = "Label"
//                id = R.id.text
//            }

            val view = LayoutInflater.from(this).inflate(R.layout.fragment_card_back, null)

            val transition =
                TransitionInflater.from(this).inflateTransition(R.transition.auto_transition)

            when (index) {
                0 -> TransitionManager.beginDelayedTransition(activity.mainLayout, Fade(Fade.IN))
                1 -> TransitionManager.beginDelayedTransition(activity.mainLayout, AutoTransition())
                2 -> TransitionManager.beginDelayedTransition(activity.mainLayout, transition)
                3 -> TransitionManager.beginDelayedTransition(
                    activity.mainLayout,
                    ChangeImageTransform().apply { duration = 2000 })
                4 -> TransitionManager.beginDelayedTransition(
                    activity.mainLayout,
                    ChangeScroll().apply { duration = 2000 })
                5 -> TransitionManager.beginDelayedTransition(
                    activity.mainLayout,
                    ChangeTransform().apply { duration = 2000 })
                6 -> TransitionManager.beginDelayedTransition(
                    activity.mainLayout,
                    Explode().apply { duration = 2000 })
                7 -> TransitionManager.beginDelayedTransition(
                    activity.mainLayout,
                    Hold().apply { duration = 2000 })
                8 -> TransitionManager.beginDelayedTransition(
                    activity.mainLayout,
                    Slide().apply { duration = 2000 })
            }
            index++
            if (index > 8) {
                index = startIndex
            }
            activity.mainLayout.addView(view)
        }
    }
}