package com.android.demo.widget.canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.android.demo.R
import com.android.demo.utils.factory.PaintFactory

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-12-16
 */
class BitmapShaderView : View {

    private var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
    private val paint = PaintFactory.createFillPaint(Color.BLACK)
    private val bitmapShader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR)
    private lateinit var linearGradient: LinearGradient

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        linearGradient = LinearGradient(0f, 0f, measuredWidth.toFloat(), 0f, Color.RED, Color.BLUE, Shader.TileMode.REPEAT)
        val composeShader = ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.SRC_ATOP)
        paint.shader = composeShader
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRect(Rect(0, 0, measuredWidth, measuredHeight), paint)
    }
}