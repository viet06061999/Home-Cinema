package com.sun.homecinema.ui.movies

import android.view.View
import androidx.navigation.fragment.findNavController
import com.sun.homecinema.R
import com.sun.homecinema.databinding.FragmentMoviesBinding
import com.sun.homecinema.base.BindingFragment
import com.sun.homecinema.utils.setHeight
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MoviesFragment : BindingFragment<FragmentMoviesBinding>(), View.OnClickListener {

    override fun getLayoutResId() = R.layout.fragment_movies

    override val viewModel by sharedViewModel<GenreViewModel>()

    override fun setupView() {
        initListener()
        binding.apply {
            imageAction.setHeight(0.2f)
            imageComedy.setHeight(0.2f)
            imageHorror.setHeight(0.2f)
            imageRomance.setHeight(0.2f)
            imageSci.setHeight(0.2f)
            imageThriller.setHeight(0.2f)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.imageAction.id -> {
                viewModel.getActionMovies()
                navigateToList(TITLE_ACTION)
            }
            binding.imageThriller.id -> {
                viewModel.getThrillerMovies()
                navigateToList(TITLE_THRILLER)
            }
            binding.imageSci.id -> {
                viewModel.getSciMovies()
                navigateToList(TITLE_SCI)
            }
            binding.imageRomance.id -> {
                viewModel.getRomanceMovies()
                navigateToList(TITLE_ROMANCE)
            }
            binding.imageHorror.id -> {
                viewModel.getHorrorMovies()
                navigateToList(TITLE_HORROR)
            }
            binding.imageComedy.id -> {
                viewModel.getComedyMovies()
                navigateToList(TITLE_COMEDY)
            }
        }
    }

    private fun initListener() {
        binding.apply {
            imageAction.setOnClickListener(this@MoviesFragment)
            imageThriller.setOnClickListener(this@MoviesFragment)
            imageSci.setOnClickListener(this@MoviesFragment)
            imageRomance.setOnClickListener(this@MoviesFragment)
            imageHorror.setOnClickListener(this@MoviesFragment)
            imageComedy.setOnClickListener(this@MoviesFragment)
        }
    }

    private fun navigateToList(title: String) {
        val action = MoviesFragmentDirections.actionGenreToList(title)
        findNavController().navigate(action)
    }


    companion object {
        private const val TITLE_ACTION = "Action Movies"
        private const val TITLE_THRILLER = "Thriller Movies"
        private const val TITLE_SCI = "SCI-FI Movies"
        private const val TITLE_HORROR = "Horror Movies"
        private const val TITLE_ROMANCE = "Romance Movies"
        private const val TITLE_COMEDY = "Comedy Movies"
    }
}
