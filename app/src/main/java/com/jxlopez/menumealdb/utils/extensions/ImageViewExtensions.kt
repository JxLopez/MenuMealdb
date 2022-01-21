package com.jxlopez.menumealdb.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jxlopez.menumealdb.R

fun ImageView.loadImageUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .error(R.drawable.ic_placeholder_image)
        .placeholder(R.drawable.ic_placeholder_image)
        .into(this)
}