package com.android.demo.activity.demo

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * <p>
 *
 * @author anlc
 * @date 2021/1/19
 */
class IntentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this).also {
            it.textSize = 26f
            it.text = "打开目标页面"
        }
        setContentView(textView)

        textView.setOnClickListener {
            val intent = Intent(this@IntentActivity, Intent2Activity::class.java)
            intent.putExtra("intent_data", "data===")
            val bundle = Bundle()
            bundle.putString("intent_data", "------")
            startActivity(intent, bundle)
        }
    }
}