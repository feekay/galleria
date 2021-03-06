package com.lemonade.ph.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.lemonade.ph.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageFromUrl")
fun ImageView.bindImageViewImage(source: String) {
    Picasso.get().load(source).placeholder(R.drawable.ic_gallery_placeholder).into(this)
}

@BindingAdapter("isVisible")
fun View.bindVisibility(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.GONE
}