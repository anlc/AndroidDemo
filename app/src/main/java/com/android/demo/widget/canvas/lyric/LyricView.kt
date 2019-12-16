package com.android.demo.widget.canvas.lyric

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import com.android.demo.utils.factory.PaintFactory

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-12-09
 */
class LyricView : View {

    private var paint = PaintFactory.createStrokePaint(Color.BLACK)
    private var centerX = 0f
    private var centerY = 0f
    private val text = "再见只是陌生人"

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centerX = (w / 2).toFloat()
        centerY = (h / 2).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawText(text, centerX, centerY, paint)
    }
}