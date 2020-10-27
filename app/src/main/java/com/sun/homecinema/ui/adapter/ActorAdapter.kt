package com.sun.homecinema.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.homecinema.base.BaseAdapter
import com.sun.homecinema.base.BaseViewHolder
import com.sun.homecinema.data.model.Actor
import com.sun.homecinema.databinding.ItemActorBinding

class ActorAdapter(private val listener: (Actor) -> Unit) :
    BaseAdapter<Actor, ItemActorBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Actor, ItemActorBinding> {
        val itemView = ItemActorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ActorHolder(itemView, listener)
    }

    class ActorHolder(
        private val itemActorBinding: ItemActorBinding,
        listener: (Actor) -> Unit
    ) : BaseViewHolder<Actor, ItemActorBinding>(itemActorBinding, listener) {
        override fun onBind(itemData: Actor) {
            super.onBind(itemData)
            itemActorBinding.actor = itemData
        }
    }
}
