package com.android.demo.activity.canvas

import android.os.Bundle
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.widget.canvas.lyric.BombView
import java.util.*


/**
 * <p>
 *
 * @author anlc
 * @date 2019-12-10
 */
class ClipActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clip)

        val bombView = findViewById<BombView>(R.id.bombView)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                bombView.postInvalidate()
            }
        }, 200, 100)
    }
}