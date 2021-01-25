package com.android.demo.activity.demo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2021/1/19
 */
class Intent2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(TextView(this).also { it.text = "目标页面" })

        val stringExtra = intent.getStringExtra("intent_data")
        Log.d(Companion.TAG, "onCreate: $stringExtra")
    }

    companion object {
        const val TAG = "Intent2Activity"
    }
}