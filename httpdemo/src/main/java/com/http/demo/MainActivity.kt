package com.http.demo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.iv_bitmap)

        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.arrow_right_icon)
        setImage(imageView, bitmap)
        bitmap.recycle()
    }

    private fun setImage(imageView: ImageView?, bitmap: Bitmap?) {
        if (bitmap == null || bitmap.isRecycled) {
            Log.i(Companion.TAG, "setImage: bitmap error")
            return
        }
        imageView?.setImageBitmap(bitmap)
    }

    companion object {
        private const val TAG = "MainDemoActivity"
    }
}
