package com.sun.homecinema.utils

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager

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

fun View.showSoftKeyboard(activity: Activity) {
    println("hide")
    val imm =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    println(imm)
   val bl = imm.hideSoftInputFromWindow(
        this.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
    println(bl)
}
