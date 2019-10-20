package com.android.demo.activity.animation

import android.graphics.*
import android.os.Bundle
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.utils.factory.PaintFactory
import kotlinx.android.synthetic.main.activity_canvas.*

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-10-03
 */
class CanvasActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_canvas)

        drawTest()
        drawBitmap()
        drawRoundImageView()
        drawArc()
    }

    private fun drawArc() {
        val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_4444)
        val canvas = Canvas(bitmap)
        val paint = PaintFactory.createStrokePaint(Color.GRAY)
        val rectF = RectF(0f, 0f, 200f, 100f)
        canvas.drawOval(rectF, paint)
        paint.color = Color.RED
        paint.strokeWidth = 3f
        canvas.drawArc(rectF, -30f, 30f, false, paint)
        arcImageView.setImageBitmap(bitmap)
    }

    private fun drawRoundImageView() {
        val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_4444)
        val canvas = Canvas(bitmap)
        val resource = BitmapFactory.decodeResource(resources, R.mipmap.timg)

        val paint = Paint()
        paint.shader = BitmapShader(resource, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR)

        canvas.drawRoundRect(RectF(0f, 0f, 300f, 300f), 30f, 50f, paint)
        roundImageView.setImageBitmap(bitmap)
    }

    private fun drawBitmap() {
        val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_4444)
        val canvas = Canvas(bitmap)
        val resource = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher1)

        val width = resource.width
        val height = resource.height
        val srcRect = Rect(0, 0, width, height)
        val dstRect = Rect(0, 0, width * 2, height * 2)

        canvas.drawBitmap(resource, srcRect, dstRect, null)
        canvas.drawBitmap(resource, 0f, 0f, null)
        drawBitmapView.setImageBitmap(bitmap)
    }

    private fun drawTest() {
        val bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val paint = PaintFactory.createFillPaint(Color.BLACK)
        paint.textSize = 30f
        paint.textSkewX = 0.5f
        paint.textAlign = Paint.Align.LEFT
        paint.isUnderlineText = true
        canvas.drawText("canvas text", 20f, 20f, paint)

        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = 20f
        canvas.drawRect(Rect(50, 50, 200, 200), paint)

        imageView.setImageBitmap(bitmap)
    }
}