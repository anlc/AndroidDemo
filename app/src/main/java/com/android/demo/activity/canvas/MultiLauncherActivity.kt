package com.android.demo.activity.canvas

import android.os.Bundle
import android.view.View
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.widget.group.MultiLauncherLayout

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

        val multiLauncher = findViewById<MultiLauncherLayout>(R.id.multiLauncher)
        findViewById<View>(R.id.next).setOnClickListener { multiLauncher.moveToNext() }
        findViewById<View>(R.id.next).setOnClickListener { multiLauncher.moveToPrevious() }
    }
}