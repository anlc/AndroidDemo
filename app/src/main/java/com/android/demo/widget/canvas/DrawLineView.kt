package com.android.demo.widget.canvas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.android.demo.utils.factory.PaintFactory

/**
 * <p>
 *
 * @author anlc
 * @date 2019-12-16
 */

class DrawLineView : View {

    private val paint = PaintFactory.createStrokePaint(Color.BLACK)
    private lateinit var bitmap: Bitmap
    private var bitmapCanvas: Canvas? = null

    private var prevX = 0f
    private var prevY = 0f
    private var curX = 0f
    private var curY = 0f

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        paint.strokeWidth = 5f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
            bitmapCanvas = Canvas(bitmap)
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                prevX = event.x
                prevY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                curX = event.x
                curY = event.y
                bitmapCanvas?.drawLine(prevX, prevY, curX, curY, paint)
                invalidate()
                prevX = curX
                prevY = curY
            }
            MotionEvent.ACTION_UP -> {
                invalidate()
            }
        }
        return true
    }
}