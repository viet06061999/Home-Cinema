package com.sun.homecinema.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.widget.Toast
import com.sun.homecinema.R
import java.lang.Exception

fun Activity.createProgressDialog() = Dialog(this).apply {
    setContentView(R.layout.progress_dialog)
    setCancelable(false)
    setCanceledOnTouchOutside(false)
}

fun Context.showToast(obj: Any) {
    val msg = when (obj) {
        is Int -> getString(obj)
        is Exception -> obj.message.toString()
        else -> obj.toString()
    }
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
