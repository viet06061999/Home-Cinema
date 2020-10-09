package com.sun.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import java.lang.IllegalStateException

abstract class BindingFragment<T : ViewDataBinding> : Fragment() {
    @LayoutRes
    abstract fun getLayoutResId(): Int

    private var _binding: T? = null

    protected val binding: T
        get() = _binding ?: throw IllegalStateException(EXCEPTION)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        DataBindingUtil
            .inflate<T>(inflater, getLayoutResId(), container, false)
            .apply { _binding = this }.root

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        private const val EXCEPTION = "Binding only is valid after onCreateView"
    }
}
