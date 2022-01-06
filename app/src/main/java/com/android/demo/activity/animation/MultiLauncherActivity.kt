package com.android.demo.activity.animation

import android.os.Bundle
import com.android.demo.R
import com.android.demo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_multi_launcher.*

/**
 * <p>
 *
 * @author anlc
 * @date 2019-11-27
 */
class MultiLauncherActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_launcher)

        next.setOnClickListener { multiLauncher.moveToNext() }
        prev.setOnClickListener { multiLauncher.moveToPrevious() }
    }
}