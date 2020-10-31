package com.sun.homecinema.base

import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.sun.homecinema.data.model.GeneraEntity

abstract class BaseAdapter<T : GeneraEntity, B : ViewBinding>(
    private val listener: (T) -> Unit,
    private val loadMore: () -> Unit
) : ListAdapter<T, BaseViewHolder<T, B>>(BaseDiffUtil<T>()),
    BindableAdapter<List<T>> {

    override fun onBindViewHolder(holder: BaseViewHolder<T, B>, position: Int) {
        holder.onBind(getItem(position))
        if (position == currentList.size - 5) {
            loadMore()
        }
    }

    override fun setData(data: List<T>?) {
        submitList(data)
    }
}
