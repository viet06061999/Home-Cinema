package com.sun.homecinema.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.homecinema.base.BaseAdapter
import com.sun.homecinema.base.BaseViewHolder
import com.sun.homecinema.data.model.MovieWithActors
import com.sun.homecinema.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    private val listener: (MovieWithActors) -> Unit,
    private val onClick: (View, MovieWithActors) -> Unit
) :
    BaseAdapter<MovieWithActors, ItemFavoriteBinding>(listener, {}) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MovieWithActors, ItemFavoriteBinding> {
        val itemView = ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriteHolder(itemView, listener, onClick)
    }

    class FavoriteHolder(
        private val ItemFavoriteBinding: ItemFavoriteBinding,
        listener: (MovieWithActors) -> Unit,
        private val onClick: (View, MovieWithActors) -> Unit
    ) : BaseViewHolder<MovieWithActors, ItemFavoriteBinding>(ItemFavoriteBinding, listener) {
        override fun onBind(itemData: MovieWithActors) {
            super.onBind(itemData)
            ItemFavoriteBinding.movie = itemData
            ItemFavoriteBinding.buttonDelete.setOnClickListener {
                onClick(it, itemData)
            }
        }
    }
}
