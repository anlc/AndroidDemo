package com.android.demo.activity.demo

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.android.demo.R

/**
 * <p>
 *
 * @author anlc
 * @date 2021/1/4
 */
class OnClickActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_click)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(TAG, "dispatchTouchEvent: activity")
        return super.dispatchTouchEvent(ev)
    }


    class OnClickView @JvmOverloads constructor(
            context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : View(context, attrs, defStyleAttr) {
        init {
            Log.d(TAG, "OnClickView: init view")
        }

        override fun onTouchEvent(event: MotionEvent?): Boolean {
            Log.d(TAG, "onTouchEvent: view")
            return super.onTouchEvent(event)
        }
    }

    class OnClickViewGroup @JvmOverloads constructor(
            context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : FrameLayout(context, attrs, defStyleAttr) {

        override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
            Log.d(TAG, "onLayout: view group")
        }

        override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            Log.d(TAG, "onMeasure: view group")
        }

        override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
            Log.d(TAG, "dispatchTouchEvent: view group")
            return super.dispatchTouchEvent(ev)
        }
    }

    companion object {
        const val TAG = "OnClickActivity"
    }
}
