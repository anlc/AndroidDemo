package com.android.demo.activity.animation

import android.os.Bundle
import android.view.View
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.widget.group.DrawerLayout

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

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        findViewById<View>(R.id.leftView).setOnClickListener { drawerLayout.closeDrawer() }
        findViewById<View>(R.id.contentView).setOnClickListener { drawerLayout.openDrawer() }
    }
}