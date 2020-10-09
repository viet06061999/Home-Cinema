package com.sun.mvvm.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T, B : ViewBinding>(
    binding: B,
    onItemClick: (T) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var itemData: T? = null

    init {
        binding.root.setOnClickListener {
            itemData?.let { onItemClick(it) }
        }
    }

    open fun onBind(itemData: T) {
        this.itemData = itemData
    }
}
