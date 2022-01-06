package com.android.demo.activity.jetpack

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.demo.R
import com.android.demo.databinding.ActivityBindingListBinding
import com.android.demo.databinding.ItemBindingListBinding

/**
 * <p>
 *
 * @author anlc
 * @date 2021/2/3
 */
class BindingListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_binding_list) as ActivityBindingListBinding
        val rvList = contentView.rvList
        rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        rvList.adapter = BaseAdapter<SimpleViewHolder> {}
    }

    class Adapter(val context: Context, layoutId: Int) : RecyclerView.Adapter<SimpleViewHolder>() {
        private lateinit var data: List<String>
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
            val inflater = LayoutInflater.from(context)
            val binding = DataBindingUtil.inflate(inflater, R.layout.item_binding_list, parent, false) as ItemBindingListBinding
            return SimpleViewHolder(binding.root)
        }

        override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
            val binding = DataBindingUtil.getBinding<ItemBindingListBinding>(holder.itemView) as ItemBindingListBinding
            binding.data = data[position]
        }

        override fun getItemCount(): Int {
            return data.size;
        }

    }

    class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}