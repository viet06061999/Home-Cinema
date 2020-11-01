package com.sun.homecinema.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.homecinema.base.BaseAdapter
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.databinding.ItemPopularBinding
import com.sun.homecinema.utils.setWith
import com.sun.homecinema.base.BaseViewHolder
import com.sun.homecinema.utils.setHeight

class PopularAdapter(
    private val listener: (Movie) -> Unit,
    loadMore: () -> Unit
) : BaseAdapter<Movie, ItemPopularBinding>(listener, loadMore) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie, ItemPopularBinding> {
        val itemView = ItemPopularBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        itemView.root.setWith(0.4f)
        return PopularHolder(itemView, listener)
    }

    class PopularHolder(
        private val itemPopularBinding: ItemPopularBinding,
        listener: (Movie) -> Unit
    ) : BaseViewHolder<Movie, ItemPopularBinding>(itemPopularBinding, listener) {
        override fun onBind(itemData: Movie) {
            super.onBind(itemData)
            itemPopularBinding.imagePopular.setHeight(0.15f)
            itemPopularBinding.movie = itemData
        }
    }
}
