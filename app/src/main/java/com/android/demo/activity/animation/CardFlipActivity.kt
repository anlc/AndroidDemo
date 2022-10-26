package com.android.demo.activity.animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.android.demo.R
import com.android.demo.base.BaseActivity
import com.android.demo.databinding.ActivityCardFlipBinding

class CardFlipActivity : BaseActivity() {

    class CardFrontFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? = inflater.inflate(R.layout.fragment_card_front, container, false)
    }

    class CardBackFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? = inflater.inflate(R.layout.fragment_card_back, container, false)
    }

    private var showingBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = DataBindingUtil.setContentView<ViewDataBinding>(
            this,
            R.layout.activity_card_flip
        ) as ActivityCardFlipBinding

        supportFragmentManager.beginTransaction()
            .add(R.id.container, CardFrontFragment())
            .commit()

        activity.container.setOnClickListener {
            flipCard()
        }
    }

    private fun flipCard() {
        if (showingBack) {
            supportFragmentManager.popBackStack()
            return
        }

        showingBack = true
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.animator.card_flip_right_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_left_in,
                R.animator.card_flip_left_out
            )
            .replace(R.id.container, CardBackFragment())
            .addToBackStack(null)
            .commit()
    }
}