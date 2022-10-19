package com.android.demo.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import com.android.demo.R

class TitleSeekBar(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val seekBar: SeekBar?
        get() {
            return findViewById(R.id.duration)
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_title_seekbar, this)
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.TitleSeekBar)
        val title = typedArray?.getString(R.styleable.TitleSeekBar_title)
        val titleView = findViewById<TextView>(R.id.title_view)
        titleView.apply {
            (title + "400").also { text = it }
        }
        seekBar?.apply {
            max = typedArray?.getInt(R.styleable.TitleSeekBar_max, 400)!!
            progress = max
            Log.i("TitleSeekBar", "max: $max")
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    (title + progress).also { titleView.text = it }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            })
        }
    }

    fun getValue() = seekBar?.progress
}