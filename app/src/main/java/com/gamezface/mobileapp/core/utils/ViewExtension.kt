package com.gamezface.mobileapp.core.utils

import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gamezface.mobileapp.R

fun View.hide() {
    this.visibility = GONE
}

fun View.show() {
    this.visibility = VISIBLE
}

fun ImageView.loadImage(context: Context?, url: String?) {
    val placeholder = listOf(R.color.duck_egg_blue, R.color.light_pink, R.color.cream).random()
    context?.let {
        Glide.with(context)
            .load(url)
            .apply(RequestOptions().placeholder(placeholder))
            .into(this)
    }
}
