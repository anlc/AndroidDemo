package com.android.demo.widget.group

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.Scroller
import kotlin.math.abs
import kotlin.properties.Delegates

/**
 * <p>
 *
 * @author anlc
 * @date 2019-11-28
 */
class MultiLauncherLayout : ViewGroup {

    private val scroller by lazy { Scroller(context) }
    private val touchSlop by lazy { ViewConfiguration.get(context).scaledDoubleTapSlop }
    private var screenWidth by Delegates.notNull<Int>()
    private var currentScreen = 0
    private var touchState = TOUCH_STOP
    private var lastMotionX = 0
    private var velocityTracker: VelocityTracker? = null

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec) * childCount, MeasureSpec.getSize(heightMeasureSpec))
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        screenWidth = width / childCount
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.layout(i * screenWidth, 0, (i + 1) * width, b - t)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (velocityTracker == null) velocityTracker = VelocityTracker.obtain()

        val velocityTracker: VelocityTracker = this.velocityTracker!!
        velocityTracker.addMovement(event)
        super.onTouchEvent(event)

        val x = event.x.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (!scroller.isFinished) scroller.abortAnimation()
                lastMotionX = x
            }
            MotionEvent.ACTION_MOVE -> {
                if (abs(x - lastMotionX) > touchSlop) {
                    scrollBy(lastMotionX - x, 0)
                    lastMotionX = x
                }
            }
            MotionEvent.ACTION_UP -> {
                velocityTracker.computeCurrentVelocity(1000)
                val xVelocity = velocityTracker.xVelocity
                when {
                    xVelocity > 0 -> moveToPrevious()
                    xVelocity < childCount - 1 -> moveToNext()
                    else -> moveToDestination()
                }
                touchState = TOUCH_STOP

                this.velocityTracker?.clear()
                this.velocityTracker?.recycle()
                this.velocityTracker = null
            }
            MotionEvent.ACTION_CANCEL -> touchState = TOUCH_STOP
        }
        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_MOVE && touchState == TOUCH_FLING) return true
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                lastMotionX = ev.x.toInt()
                touchState = if (scroller.isFinished) TOUCH_STOP else TOUCH_FLING
            }
            MotionEvent.ACTION_MOVE -> {
                if (abs(ev.x - lastMotionX) > touchSlop) {
                    touchState = TOUCH_FLING
                }
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> touchState = TOUCH_STOP
        }
        return touchState == TOUCH_FLING
    }

    private fun moveToDestination() {
        val index = (scrollX + screenWidth / 2) / screenWidth
        moveToScreen(index)
    }

    private fun moveToScreen(whichScreen: Int) {
        if (currentScreen == whichScreen) return
        currentScreen = whichScreen
        if (currentScreen > childCount - 1) currentScreen = childCount - 1
        if (currentScreen < 0) currentScreen = 0

        val dx = currentScreen * screenWidth - scrollX
        Log.d(TAG, "currentScreen: $currentScreen  scrollX: $scrollX dx: $dx")
        scroller.startScroll(scrollX, scrollY, dx, 0, abs(dx))
        invalidate()
    }

    override fun computeScroll() {
        if (scroller.computeScrollOffset()) {
            Log.d(TAG, "currX: ${scroller.currX} currY: ${scroller.currY}")
            scrollTo(scroller.currX, scroller.currY)
            invalidate()
        }
    }

    fun moveToNext() {
        moveToScreen(currentScreen + 1)
    }

    fun moveToPrevious() {
        moveToScreen(currentScreen - 1)
    }

    companion object {
        const val TAG = "MultiLauncherLayout"
        const val TOUCH_STOP = 0x01
        const val TOUCH_FLING = 0x02
    }
}