package com.sun.homecinema.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.homecinema.base.BaseAdapter
import com.sun.homecinema.base.BaseViewHolder
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.databinding.ItemMoviePosterBinding
import com.sun.homecinema.utils.hide
import com.sun.homecinema.utils.setHeight

class RecommendAdapter(private val listener: (Movie) -> Unit) :
    BaseAdapter<Movie, ItemMoviePosterBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie, ItemMoviePosterBinding> {
        val itemView = ItemMoviePosterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        itemView.root.setHeight(0.4f)
        return RecommendHolder(itemView, listener)
    }

    class RecommendHolder(
        private val itemMoviePosterBinding: ItemMoviePosterBinding,
        listener: (Movie) -> Unit
    ) : BaseViewHolder<Movie, ItemMoviePosterBinding>(itemMoviePosterBinding, listener) {
        override fun onBind(itemData: Movie) {
            super.onBind(itemData)
            itemMoviePosterBinding.textReleaseDate.hide()
            itemMoviePosterBinding.movie = itemData
        }
    }
}
