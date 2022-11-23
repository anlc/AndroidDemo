package com.android.demo.activity.animation

import android.graphics.Camera
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.SizeF
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.Transformation
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.databinding.ActivityDiagonalFlipBinding
import kotlin.math.sqrt

class DiagonalFlipActivity : BaseActivity() {

    private var isFlip = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity =
            DataBindingUtil.setContentView<ViewDataBinding>(
                this,
                R.layout.activity_diagonal_flip
            ) as ActivityDiagonalFlipBinding

        activity.start.setOnClickListener {
            val animationView = activity.animationView
            animationView.clearAnimation()

            val fromDegree = if (isFlip) 180f else 0f
            var degree = if (animationView.rotationX == 0f) 45f else -45f
            isFlip = !isFlip

            Log.i(TAG, "onCreate: fromDegree: $fromDegree, degree: $degree")

            animationView.animation = DiagonalFlipAnimation(
                fromDegree,
                180f - fromDegree,
                degree
            ).apply {
                duration = resources.getInteger(R.integer.medium_anim_time).toLong()
                interpolator = LinearInterpolator()
                fillAfter = true
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                        it.isClickable = false
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        it.isClickable = true
                    }

                    override fun onAnimationRepeat(animation: Animation?) {
                    }
                })
                start()
            }
        }
    }

    companion object {
        private const val TAG = "DiagonalFlipActivity"
    }
}

class DiagonalFlipAnimation(
    private val fromDegree: Float,
    private val toDegree: Float,
    private val rotate: Float
) : Animation() {

    private val sqrtValue = sqrt(2f)
    private lateinit var camera: Camera
    private lateinit var sizeF: SizeF

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        sizeF = SizeF(width / sqrtValue, height / sqrtValue)
        camera = Camera()
        camera.setLocation(0f, 0f, width.toFloat())
        super.initialize(width, height, parentWidth, parentHeight)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        val matrix = t?.matrix

        camera.save()
        camera.rotateX(fromDegree + ((toDegree - fromDegree) * interpolatedTime))
        camera.getMatrix(matrix)
        camera.restore()

        if (rotate <= 0f) {
            matrix?.preTranslate(-sizeF.width, -sizeF.height)
            matrix?.postTranslate(sizeF.width, sizeF.height)
        }

        matrix?.preRotate(-rotate)
        matrix?.postRotate(rotate)
    }

}

