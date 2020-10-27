package com.sun.homecinema.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sun.homecinema.R
import com.sun.homecinema.utils.ApiConfig

@BindingAdapter("image")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(ApiConfig.getUrlImage(imageUrl))
        .error(R.drawable.ic_error)
        .into(view)
}
