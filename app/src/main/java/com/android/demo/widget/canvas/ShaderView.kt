package com.android.demo.widget.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import com.android.demo.utils.factory.PaintFactory

/**
 * <p>
 *
 * @author anlc
 * @date 2019-12-16
 */
class ShaderView : View {

    private val paint = PaintFactory.createFillPaint(Color.BLACK)
    private val text = "Android 开发"
    private val text2 = "Android 绘图技术"

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)


    override fun onDraw(canvas: Canvas) {
        paint.textSize = 80f
        setLayerType(LAYER_TYPE_HARDWARE, paint)
        paint.setShadowLayer(10f, 0f, 0f, Color.RED)
        canvas.drawText(text, 100f, 100f, paint)
        paint.setShadowLayer(10f, 20f, 15f, Color.BLUE)
        canvas.drawText(text2, 100f, 300f, paint)
    }
}