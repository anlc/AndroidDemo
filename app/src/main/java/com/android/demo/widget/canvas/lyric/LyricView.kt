package com.android.demo.widget.canvas.lyric

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.RectF
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
    private var redPaint = PaintFactory.createStrokePaint(Color.RED)
    private var centerX = 0f
    private var centerY = 0f
    private val text = "再见只是陌生人"
    private var offset = 0

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centerX = (w / 2).toFloat()
        centerY = (h / 2).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
//        val textRect = getTextRect()

//        canvas.drawText(text, centerX, centerY, paint)
        drawKaraoke(canvas)
    }

    private fun getTextRect(): Rect {
        val rect = Rect()
        paint.textSize = 30f
        paint.getTextBounds(text, 0, text.length, rect)
        redPaint.textSize = 30f
        redPaint.getTextBounds(text, 0, text.length, rect)
        return rect
    }

    private fun drawKaraoke(canvas: Canvas) {
        val textRect = getTextRect()
        val startX = centerX - textRect.width() / 2
        val rectF = RectF(startX, centerY - textRect.height(), startX + offset, centerY + 10)
        val rectF2 = RectF(startX + offset, centerY - textRect.height(), startX + textRect.width(), centerY + 10)
        canvas.save()
        canvas.clipRect(rectF)
        canvas.drawText(text, startX, centerY, paint)
        canvas.restore()

        canvas.save()
        canvas.clipRect(rectF2)
        canvas.drawText(text, startX, centerY, redPaint)
        canvas.restore()
        offset += 5
        if (offset > textRect.width()) {
            offset = 0
        }
    }
}

