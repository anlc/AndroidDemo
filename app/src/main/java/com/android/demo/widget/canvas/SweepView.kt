package com.android.demo.widget.canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-12-17
 */
class SweepView : View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var rotate = 0f
    private val localMatrix = Matrix()
    private lateinit var shader: SweepGradient

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        isFocusable = true
        isFocusableInTouchMode = true

        val x = 160f
        val y = 100f
        shader = SweepGradient(x, y, intArrayOf(Color.GREEN,
                Color.RED, Color.BLUE, Color.GREEN), null)
        paint.shader = shader
    }

    override fun onDraw(canvas: Canvas) {

        val x = 160f
        val y = 100f
        canvas.translate(300f, 300f)
        canvas.drawColor(Color.WHITE)
        matrix.setRotate(rotate, x, y)
        shader.setLocalMatrix(matrix)
        rotate += 3
        if (rotate >= 360) {
            rotate = 0f
        }
        invalidate()
        canvas.drawCircle(x, y, 380f, paint)
    }
}