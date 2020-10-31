package com.sun.homecinema.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.homecinema.base.BaseAdapter
import com.sun.homecinema.base.BaseViewHolder
import com.sun.homecinema.data.model.GenreResponse
import com.sun.homecinema.databinding.ItemGenreBinding

class GenreAdapter(private val listener: (GenreResponse) -> Unit) :
    BaseAdapter<GenreResponse, ItemGenreBinding>(listener,{}) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<GenreResponse, ItemGenreBinding> =
        GenreHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    class GenreHolder(
        private val itemGenreBinding: ItemGenreBinding,
        listener: (GenreResponse) -> Unit
    ) : BaseViewHolder<GenreResponse, ItemGenreBinding>(itemGenreBinding, listener) {
        override fun onBind(itemData: GenreResponse) {
            super.onBind(itemData)
            itemGenreBinding.genre = itemData
        }
    }
}
