package com.sun.homecinema.base

import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.sun.homecinema.data.model.GeneraEntity
import com.sun.mvvm.base.BaseViewHolder

abstract class BaseAdapter<T : GeneraEntity, B : ViewBinding>(private val listener: (T) -> Unit) :
    ListAdapter<T, BaseViewHolder<T, B>>(BaseDiffUtil<T>()) {

    override fun onBindViewHolder(holder: BaseViewHolder<T, B>, position: Int) {
        holder.onBind(getItem(position))
    }
}
