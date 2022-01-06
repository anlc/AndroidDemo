package com.android.demo.widget.canvas.lyric

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.android.demo.R
import com.android.demo.utils.factory.PaintFactory

/**
 * <p>
 *
 * @author anlc
 * @date 2019-12-11
 */

class BombView : View {

    private val paint = PaintFactory.createFillPaint(Color.BLACK)
    private val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.bomb)
    private var i = 0
    private var x = 30
    private var interval = 5

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onDraw(canvas: Canvas) {

        val bitmapWidth = bitmap.width / 5

        val rect = Rect(0, 0, 200, bitmap.height)
        canvas.save()
        canvas.clipRect(rect)
        canvas.translate(100f, 100f)
        canvas.drawBitmap(bitmap, -i * bitmapWidth.toFloat(), 0f, paint)
        canvas.restore()
        i++  //i 加 1 以播放下一帧
        if (i == 6) i = 0 //播放完毕后将 i 重置为 0 重新播放

        canvas.save()
        canvas.translate(0f, 100f + bitmap.height)
        canvas.drawCircle(x.toFloat(), 10f, 10f, paint)
        canvas.restore()
        Log.e("tag", "x : " + x)
        if (x > 300) interval = -5
        if (x < 20) interval = 5
        x += interval
    }
}