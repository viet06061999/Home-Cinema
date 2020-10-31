package com.sun.homecinema.utils

import android.app.Activity
import android.os.Handler
import android.util.DisplayMetrics
import android.view.View
import androidx.core.view.isVisible

fun View.setWith(percent: Float) {
    val displayMetrics = DisplayMetrics()
    (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
    layoutParams.width = (displayMetrics.widthPixels * percent).toInt()
}

fun View.setHeight(percent: Float) {
    val displayMetrics = DisplayMetrics()
    (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
    layoutParams.height = (displayMetrics.heightPixels * percent).toInt()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.showAndHide() {
    this.visibility = View.VISIBLE
    Handler().postDelayed(
        {
            this.hide()
        },
        1000
    )
}

fun View.hide() {
    this.visibility = View.GONE
}
