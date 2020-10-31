package com.sun.homecinema.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.homecinema.base.BaseAdapter
import com.sun.homecinema.base.BaseViewHolder
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.data.model.SearchResponse
import com.sun.homecinema.databinding.ItemSearchBinding
import com.sun.homecinema.databinding.ItemWatchMoreBinding
import com.sun.homecinema.utils.setHeight

class SearchAdapter(private val listener: (SearchResponse) -> Unit) :
    BaseAdapter<SearchResponse, ItemSearchBinding>(listener,{}) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<SearchResponse, ItemSearchBinding> {
        val itemView = ItemSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchHolder(itemView, listener)
    }

    class SearchHolder(
        private val itemWatchMoreBinding: ItemSearchBinding,
        listener: (SearchResponse) -> Unit
    ) : BaseViewHolder<SearchResponse, ItemSearchBinding>(itemWatchMoreBinding, listener) {
        override fun onBind(itemData: SearchResponse) {
            super.onBind(itemData)
            itemWatchMoreBinding.search = itemData
        }
    }
}
