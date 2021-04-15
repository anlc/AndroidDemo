package com.simple.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvActivityList = findViewById<RecyclerView>(R.id.rvActivityList)
        rvActivityList.adapter = object : Adapter<SimpleViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
                val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.item_activity, parent, false)
                return SimpleViewHolder(view)
            }

            override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {

            }

            override fun getItemCount(): Int = 30

        }
    }
}