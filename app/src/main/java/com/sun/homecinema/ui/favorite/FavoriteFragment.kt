package com.sun.homecinema.ui.favorite

import android.view.View
import androidx.navigation.fragment.findNavController
import com.sun.homecinema.R
import com.sun.homecinema.databinding.FragmentFavoriteBinding
import com.sun.homecinema.base.BindingFragment
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.data.model.MovieWithActors
import com.sun.homecinema.ui.adapter.FavoriteAdapter
import com.sun.homecinema.ui.adapter.WatchMoreAdapter
import com.sun.homecinema.ui.detail.DetailMovieFragmentDirections
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoriteFragment : BindingFragment<FragmentFavoriteBinding>() {

    override fun getLayoutResId() = R.layout.fragment_favorite

    override val viewModel by sharedViewModel<FavoriteViewModel>()

    private val favoriteAdapter = FavoriteAdapter(::onItemFavoriteClick, ::onDeleteClick)

    override fun setupView() {
        binding.apply {
            lifecycleOwner = this@FavoriteFragment
            favoriteVM = viewModel
            recyclerViewFavorite.adapter = favoriteAdapter
        }
    }

    private fun onItemFavoriteClick(item: MovieWithActors) {
        val action = FavoriteFragmentDirections.actionFavoriteToDetail(item.movie.movieId)
        findNavController().navigate(action)
    }

    private fun onDeleteClick(view: View, movie: MovieWithActors) {
        if (view.id == R.id.buttonDelete) {
            viewModel.deleteFavorite(movie)
        }
    }
}
