package com.android.demo.activity.jetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.android.demo.R
import com.android.demo.databinding.ActivityViewModelBinding

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2021/1/5
 */
class ObservableActivity : AppCompatActivity() {

    private val mTitle = ObservableField<String>()
    private val mField = ObservableJavaActivity.ObservableFieldBean("item", ObservableInt(0))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityViewModelBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_model)

        mTitle.set("default title")
        binding.title = mTitle.get()
        binding.observableFieldBean = mField

        binding.tvTitle.setOnClickListener {
//            mTitle.set("xxx" + mField.count.get())
            mField.name = ("item" + mField.count.get())
            mField.count.set(mField.count.get() + 1)
        }
    }

}