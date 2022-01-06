package com.android.demo.activity.animation

import android.os.Bundle
import com.android.demo.R
import com.android.demo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_custom_drawer.*

/**
 * <p>
 *
 * @author anlc
 * @date 2019-12-03
 */
class CustomDrawerActivity : BaseActivity (){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_drawer)

        leftView.setOnClickListener { drawerLayout.closeDrawer() }
        contentView.setOnClickListener { drawerLayout.openDrawer() }
    }
}