package com.android.demo.activity.animation

import android.os.Bundle
import com.android.demo.R
import com.android.demo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_clip.*
import java.util.*


/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-12-10
 */
class ClipActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clip)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                bombView.postInvalidate()
            }
        }, 200, 100)
    }
}