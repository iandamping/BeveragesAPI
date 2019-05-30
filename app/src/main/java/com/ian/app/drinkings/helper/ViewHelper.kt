package com.ian.app.drinkings.helper

import android.view.*
import androidx.fragment.app.FragmentActivity
import com.ian.app.drinkings.R

/**
 *
Created by Ian Damping on 30/05/2019.
Github = https://github.com/iandamping
 */

fun ViewGroup.inflates(layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun FragmentActivity.fullScreenAnimation() {
    this.overridePendingTransition(R.anim.fade_in_activity, R.anim.fade_out_activity)
    this.requestWindowFeature(Window.FEATURE_NO_TITLE)
    this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}