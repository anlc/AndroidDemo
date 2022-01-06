package com.android.demo.widget.canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.android.demo.utils.factory.PaintFactory

/**
 * <p>
 *
 * @author anlc
 * @date 2019-12-16
 */
class GradientView : View {

    private val paint = PaintFactory.createFillPaint(Color.BLACK)
    private var rect: RectF = RectF(100f, 100f, 300f, 200f)
    private var rect2 :RectF
    private var rect3 : RectF
    private var linearGradient : LinearGradient
    private var linearGradient2 : LinearGradient
    private var linearGradient3 : LinearGradient


    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        paint.strokeWidth = 3f
        linearGradient = LinearGradient(rect.left, rect.top, rect.right, rect.bottom,
                Color.RED, Color.BLUE, Shader.TileMode.CLAMP)

        rect2 = RectF(rect)
        rect2.inset(-100f, -100f)
        linearGradient2 = LinearGradient(rect2.left, rect2.top, rect2.right, rect2.bottom,
                Color.RED, Color.BLUE, Shader.TileMode.CLAMP)

        rect3 = RectF(rect)
        rect3.inset(100f, 100f)
        linearGradient3 = LinearGradient(rect3.left, rect3.top, rect3.right, rect3.bottom,
                Color.RED, Color.BLUE, Shader.TileMode.CLAMP)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(0f, 20f)
        paint.shader = linearGradient
        canvas.drawRect(rect, paint)

        canvas.translate(0f, rect.height() + OFFSET)

        paint.shader = linearGradient2
        canvas.drawRect(rect, paint)

        canvas.translate(0f, rect.height() + OFFSET)

        paint.shader = linearGradient3
        canvas.drawRect(rect, paint)
    }

    companion object {
        const val OFFSET = 100
    }
}