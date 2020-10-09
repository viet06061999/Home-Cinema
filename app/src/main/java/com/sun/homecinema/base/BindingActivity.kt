package com.sun.homecinema.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sun.homecinema.utils.showToast

abstract class BindingActivity<T : ViewDataBinding> : AppCompatActivity() {
    @LayoutRes
    abstract fun getLayoutResId(): Int

    protected lateinit var binding: T
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResId())
    }

    fun showToastMessage(message: String?) {
        if (!message.isNullOrEmpty()) {
            baseContext.showToast(message)
        }
    }

    fun showToastMessage(stringResourceId: Int) {
        showToastMessage(getString(stringResourceId))
    }
}
