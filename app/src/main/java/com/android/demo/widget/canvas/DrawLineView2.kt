package com.android.demo.widget.canvas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.android.demo.utils.factory.PaintFactory

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-12-16
 */
class DrawLineView2 : View {

    private val paint = PaintFactory.createStrokePaint(Color.BLACK)
    private var prevX = 0f
    private var prevY = 0f
    private val path = Path()
    private var bitmap: Bitmap? = null
    private var bitmapCanvas: Canvas? = null

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        paint.strokeWidth = 3f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
            bitmapCanvas = Canvas(bitmap)
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.reset()
                prevX = x
                prevY = y
                path.moveTo(x, y)
            }
            MotionEvent.ACTION_MOVE -> {
                path.quadTo(prevX, prevY, x, y)
                invalidate()
                prevX = x
                prevY = y
            }
            MotionEvent.ACTION_UP -> {
                bitmapCanvas?.drawPath(path, paint)
                invalidate()
            }
        }
        return true
    }
}