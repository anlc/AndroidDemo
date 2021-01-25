package com.android.demo.activity.jetpack

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class ViewModuleActivity : AppCompatActivity() {

    private lateinit var viewModelData: ViewModelData
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textView = TextView(this).also {
            it.textSize = 26f
        }
        setContentView(textView)

        viewModelData = ViewModelProviders.of(this).get(ViewModelData::class.java)

        textView.setOnClickListener {
            viewModelData.number++
            updateText()
        }
    }

    private fun updateText() {
        textView.text = viewModelData.number.toString()
    }

    class ViewModelData(var number: Int) : ViewModel()

//    class AndroidModelTest() : Android
}