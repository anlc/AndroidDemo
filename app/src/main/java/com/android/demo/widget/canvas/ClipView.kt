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
 * @author anlc
 * @date 2019-12-10
 */

class ClipView : View {

    private var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.timg)
    private val paint: Paint = PaintFactory.createStrokePaint(Color.BLACK)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        canvas.drawLine(0f, 300f, 200f, 300f, paint)
        canvas.translate(0f, 300f)
        canvas.clipRect(20f, 30f, 150f, 120f)

        val path = Path()
        path.addCircle(150f, 150f, 80f, Path.Direction.CCW)
        canvas.clipPath(path, Region.Op.XOR)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
    }

}