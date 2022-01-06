package com.android.demo.widget.group

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.Scroller

/**
 * <p>
 *
 * @author anlc
 * @date 2019-12-02
 */
class DrawerLayout : ViewGroup {

    private val scroller by lazy { Scroller(context) }
    private val touchSlop by lazy { ViewConfiguration.get(context).scaledTouchSlop }
    private var drawerWidth = 0
    private var isOpen = false

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun computeScroll() {
        if (scroller.computeScrollOffset()) {
            Log.d("TAG", "currX ${scroller.currX}  currY ${scroller.currY}")
            scrollTo(scroller.currX, scroller.currY)
            invalidate()
        }
    }

    fun closeDrawer() {
        if (!isOpen) return
        isOpen = false
        scroller.startScroll(scrollX, scrollY, drawerWidth, 0)
        invalidate()
    }

    fun openDrawer() {
        if (isOpen) return
        isOpen = true
        scroller.startScroll(scrollX, scrollY, -drawerWidth, 0)
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec))
    }

    class LayoutParams : MarginLayoutParams {

        var gravity = Gravity.NO_GRAVITY

        constructor(c: Context, attrs: AttributeSet?) : super(c, attrs) {
            val typedArray = c.obtainStyledAttributes(attrs, LAYOUT_ATTRS)
            gravity = typedArray.getInt(0, Gravity.NO_GRAVITY)
            typedArray.recycle()
        }

        constructor(width: Int, height: Int) : super(width, height)
        constructor(source: LayoutParams) : super(source) {
            this.gravity = source.gravity
        }

        constructor(source: MarginLayoutParams?) : super(source)
        constructor(source: ViewGroup.LayoutParams?) : super(source)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            val layoutParams = child.layoutParams as LayoutParams
            if (isCenter(layoutParams)) {
                child.layout(layoutParams.leftMargin, layoutParams.topMargin,
                        layoutParams.leftMargin + child.measuredWidth,
                        layoutParams.topMargin + child.measuredHeight)
            } else {
                drawerWidth = child.measuredWidth
                child.layout(-drawerWidth, layoutParams.topMargin,
                        0, layoutParams.topMargin + child.measuredHeight)
            }
        }
    }

    private fun isCenter(layoutParams: LayoutParams) = layoutParams.gravity == Gravity.NO_GRAVITY

    override fun generateLayoutParams(attrs: AttributeSet?): ViewGroup.LayoutParams {
        return LayoutParams(context, attrs)
    }

    override fun generateLayoutParams(p: ViewGroup.LayoutParams?): ViewGroup.LayoutParams {
        return when (p) {
            is LayoutParams -> LayoutParams(p)
            is MarginLayoutParams -> LayoutParams(p)
            else -> LayoutParams(p)
        }
    }

    override fun generateDefaultLayoutParams(): ViewGroup.LayoutParams {
        return LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun checkLayoutParams(p: ViewGroup.LayoutParams?): Boolean {
        return p is LayoutParams && super.checkLayoutParams(p)
    }

    companion object {
        val LAYOUT_ATTRS = intArrayOf(android.R.attr.layout_gravity)
    }
}