package com.sun.homecinema.base

import androidx.recyclerview.widget.DiffUtil
import com.sun.homecinema.data.model.GeneraEntity

class BaseDiffUtil<T: GeneraEntity> :  DiffUtil.ItemCallback<T>(){
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }
}
