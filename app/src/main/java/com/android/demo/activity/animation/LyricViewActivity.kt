package com.android.demo.activity.animation

import android.os.Bundle
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.widget.canvas.lyric.LyricView
import java.util.*

/**
 * <p>
 *
 * @author anlc
 * @date 2019-12-18
 */
class LyricViewActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyric_view)

        val lyricView = findViewById<LyricView>(R.id.lyricView)
        Timer().schedule(object :TimerTask(){
            override fun run() {
                lyricView.postInvalidate()
            }
        }, 200, 200)
    }
}