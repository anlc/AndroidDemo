package com.android.demo.activity.animation

import android.os.Bundle
import android.view.View
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.widget.group.ScrollerViewGroup

/**
 * <p>
 *
 * @author anlc
 * @date 2019-11-26
 */
class ScrollerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroller)

        val textView = findViewById<ScrollerViewGroup>(R.id.textView)
        findViewById<View>(R.id.btnScrollBy).setOnClickListener { textView.start() }
        findViewById<View>(R.id.btnScrollTo).setOnClickListener { textView.stop() }
    }

}