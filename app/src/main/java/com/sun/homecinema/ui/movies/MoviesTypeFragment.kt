package com.sun.homecinema.ui.movies

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sun.homecinema.R
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.databinding.FragmentMovieGenreBinding
import com.sun.homecinema.base.BindingFragment
import com.sun.homecinema.ui.adapter.MoviesGenreAdapter
import com.sun.homecinema.ui.home.MovieTypeFragmentArgs
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MoviesTypeFragment : BindingFragment<FragmentMovieGenreBinding>() {

    override fun getLayoutResId() = R.layout.fragment_movie_genre

    override val viewModel by sharedViewModel<GenreViewModel>()

    private val args: MovieTypeFragmentArgs by navArgs()

    private val moviesAdapter = MoviesGenreAdapter(::onItemClick)

    override fun setupView() {
        binding.apply {
            lifecycleOwner = this@MoviesTypeFragment
            titleFragment = args.title
            recyclerViewMoviesGenre.adapter = moviesAdapter
            recyclerViewMoviesGenre.addItemDecoration(
                MoviesGenreAdapter.SpacesItemDecoration(SPACING)
            )
            genreVM = viewModel
        }
        initListener()
    }

    private fun initListener() {
        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
            onBackPress()
        }
    }

    private fun onItemClick(item: Movie) {
        val action = MoviesTypeFragmentDirections.actionGenreListToDetail(item.movieId)
        findNavController().navigate(action)
    }

    companion object {
        const val SPACING = 20
    }
}
