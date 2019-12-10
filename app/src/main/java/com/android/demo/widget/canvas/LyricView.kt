package com.android.demo.widget.canvas

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-12-09
 */
class LyricView : View {

    private lateinit var paint: Paint

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}