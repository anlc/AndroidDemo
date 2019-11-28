package com.android.demo.activity.animation

import android.os.Bundle
import com.android.demo.R
import com.android.demo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_scroller.*

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

        btnScrollBy.setOnClickListener { textView.start() }
        btnScrollTo.setOnClickListener { textView.stop() }
    }

}