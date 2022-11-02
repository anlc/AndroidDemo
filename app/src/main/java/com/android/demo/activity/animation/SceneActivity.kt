package com.android.demo.activity.animation

import android.os.Bundle
import android.transition.Scene
import android.transition.TransitionManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.databinding.ActivitySceneBinding

class SceneActivity : BaseActivity() {

    private var mIsAScene = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activity = DataBindingUtil.setContentView<ViewDataBinding>(
            this,
            R.layout.activity_scene
        ) as ActivitySceneBinding

        val aScene = Scene.getSceneForLayout(activity.sceneRoot, R.layout.a_scene, this)
        val otherScene = Scene.getSceneForLayout(activity.sceneRoot, R.layout.other_scene, this)

        activity.start.setOnClickListener {
            if (mIsAScene) TransitionManager.go(otherScene) else TransitionManager.go(aScene)
            mIsAScene = !mIsAScene
        }


    }
}