package com.sun.homecinema.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.homecinema.base.BaseAdapter
import com.sun.homecinema.base.BaseViewHolder
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.databinding.ItemWatchMoreBinding
import com.sun.homecinema.utils.setHeight

class WatchMoreAdapter(private val listener: (Movie) -> Unit) :
    BaseAdapter<Movie, ItemWatchMoreBinding>(listener,{}) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie, ItemWatchMoreBinding> {
        val itemView = ItemWatchMoreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WatchMoreHolder(itemView, listener)
    }

    class WatchMoreHolder(
        private val itemWatchMoreBinding: ItemWatchMoreBinding,
        listener: (Movie) -> Unit
    ) : BaseViewHolder<Movie, ItemWatchMoreBinding>(itemWatchMoreBinding, listener) {
        override fun onBind(itemData: Movie) {
            super.onBind(itemData)
            itemWatchMoreBinding.movie = itemData
        }
    }
}
