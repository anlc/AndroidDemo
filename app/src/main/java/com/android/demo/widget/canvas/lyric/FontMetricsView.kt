package com.android.demo.widget.canvas.lyric

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.android.demo.utils.factory.PaintFactory

/**
 * <p>
 *
 * @author anlc
 * @date 2019-12-19
 */
class FontMetricsView : View {

    private val paint = PaintFactory.createStrokePaint(Color.BLACK)
    private val linePaint = PaintFactory.createStrokePaint(Color.RED)
    private val topPaint = PaintFactory.createStrokePaint(Color.BLUE)
    private val ascentPaint = PaintFactory.createStrokePaint(Color.GREEN)
    private val descentPaint = PaintFactory.createStrokePaint(Color.YELLOW)
    private val bottomPaint = PaintFactory.createStrokePaint(Color.CYAN)
    private val text = "再见只是陌生人"

    private var startX = 0
    private var startY = 0
    private var textWidth = 0
    private lateinit var fontMetrics: Paint.FontMetrics

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        val textBound = getTextBound()
        textWidth = textBound.width()

        linePaint.strokeWidth = 2f
        topPaint.strokeWidth = 2f
        ascentPaint.strokeWidth = 2f
        descentPaint.strokeWidth = 2f
        bottomPaint.strokeWidth = 2f

        setBackgroundColor(Color.GRAY)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        startX = measuredWidth / 2 - textWidth / 2
        startY = measuredHeight / 2
    }

    private fun getTextBound(): Rect {
        val rect = Rect()
        paint.textSize = 30f
        paint.getTextBounds(text, 0, text.length, rect)
        fontMetrics = paint.fontMetrics
        return rect
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawText(text, startX.toFloat(), startY.toFloat(), paint)
        canvas.drawLine(startX.toFloat(), startY.toFloat(), (startX + textWidth).toFloat(), startY.toFloat(), linePaint)

        // top
        canvas.drawLine(startX.toFloat(),
                startY.toFloat() + fontMetrics.top,
                (startX + textWidth).toFloat(),
                startY.toFloat() + fontMetrics.top,
                topPaint)

        // ascent
        canvas.drawLine(startX.toFloat(),
                startY.toFloat() + fontMetrics.ascent,
                (startX + textWidth).toFloat(),
                startY.toFloat() + fontMetrics.ascent,
                ascentPaint)

        // descent
        canvas.drawLine(startX.toFloat(),
                startY.toFloat() + fontMetrics.descent,
                (startX + textWidth).toFloat(),
                startY.toFloat() + fontMetrics.descent,
                descentPaint)

        // bottom
        canvas.drawLine(startX.toFloat(),
                startY.toFloat() + fontMetrics.bottom,
                (startX + textWidth).toFloat(),
                startY.toFloat() + fontMetrics.bottom,
                bottomPaint)

        // leading
        canvas.drawLine(startX.toFloat(),
                startY.toFloat() + fontMetrics.leading,
                (startX + textWidth).toFloat(),
                startY.toFloat() + fontMetrics.leading,
                linePaint)
    }
}