package com.sun.homecinema.base

import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.sun.homecinema.data.model.GeneraEntity

abstract class BaseAdapter<T : GeneraEntity, B : ViewBinding> :
    ListAdapter<T, BaseViewHolder<T, B>>(BaseDiffUtil<T>()),
    BindableAdapter<List<T>> {

    override fun onBindViewHolder(holder: BaseViewHolder<T, B>, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun setData(data: List<T>?) {
        submitList(data)
    }
}
