package com.sun.homecinema.ui.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.sun.homecinema.base.BaseAdapter
import com.sun.homecinema.base.BaseViewHolder
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.databinding.ItemMoviesGridBinding
import com.sun.homecinema.utils.setHeight


class MoviesGenreAdapter(private val listener: (Movie) -> Unit) :
    BaseAdapter<Movie, ItemMoviesGridBinding>(listener,{}) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie, ItemMoviesGridBinding> {
        val itemView = ItemMoviesGridBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        itemView.root.setHeight(0.4f)
        return MoviesGenreHolder(itemView, listener)
    }

    class MoviesGenreHolder(
        private val itemMoviesGridBinding: ItemMoviesGridBinding,
        listener: (Movie) -> Unit
    ) : BaseViewHolder<Movie, ItemMoviesGridBinding>(itemMoviesGridBinding, listener) {
        override fun onBind(itemData: Movie) {
            super.onBind(itemData)
            itemMoviesGridBinding.movie = itemData
        }
    }

    class SpacesItemDecoration(private val spacing: Int) : ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            val column: Int = position % 2
                outRect.left =
                    spacing - column * spacing / 2
                outRect.right =
                    (column + 1) * spacing / 2
                if (position < 2) {
                    outRect.top = spacing
                }
                outRect.bottom = spacing
        }
    }
}
