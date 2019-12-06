package com.android.demo.activity.animation

import android.graphics.*
import android.os.Bundle
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.utils.factory.PaintFactory
import kotlinx.android.synthetic.main.activity_path.*

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-10-04
 */

class PathActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_path)

        starView.setImageBitmap(createStar())
        pathView.setImageBitmap(createPath())
    }

    private fun createPath(): Bitmap {
        val path = Path()
        path.addRect(RectF(10f, 10f, 100f, 100f), Path.Direction.CW)
        path.addRoundRect(RectF(110f, 10f, 200f, 100f),
                floatArrayOf(10f, 30f, 20f, 40f, 30f, 60f, 50f, 100f), Path.Direction.CCW)
        path.addOval(RectF(10f, 110f, 150f, 200f), Path.Direction.CCW)
        path.addArc(RectF(110f, 110f, 250f, 200f), 0f, 60f)
        return drawPath(path, 500, 500)
    }


    private fun createStar(): Bitmap {
        val path = Path()
        path.moveTo(100f, 10f)
        path.lineTo(170f, 180f)
        path.lineTo(20f, 70f)
        path.lineTo(180f, 70f)
        path.lineTo(30f, 180f)
        path.close()
        return drawPath(path, 200, 200)
    }

    private fun drawPath(path: Path, width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = PaintFactory.createStrokePaint(Color.BLACK)
        canvas.drawPath(path, paint)
        return bitmap
    }
}