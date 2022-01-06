package com.android.demo.widget.group

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.Scroller
import android.widget.TextView
import com.android.demo.R

/**
 * <p>
 *
 * @author anlc
 * @date 2019-11-27
 */
class ScrollerViewGroup : ViewGroup {

    private val scroller by lazy { Scroller(context) }
    private val textView by lazy { TextView(context) }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        textView.text = context?.getString(R.string.scroller_test)
        textView.textSize = 16f
        textView.setTextColor(Color.WHITE)
        addView(textView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
    }

    override fun computeScroll() {
        if (scroller.computeScrollOffset()) {
            this.scrollTo(scroller.currX, scroller.currY)
            invalidate()
        }
    }

    fun start() {
        scroller.startScroll(scrollX, scrollY, -500, 0, 10000)
        invalidate()
    }

    fun stop() {
        scroller.abortAnimation()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChild(textView, widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec))
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        textView.layout(10, 10, textView.measuredWidth + 10, textView.measuredHeight + 10)
    }
}
