package com.sun.homecinema.ui.home

import com.sun.homecinema.R
import com.sun.homecinema.databinding.FragmentMovieGenreBinding
import com.sun.homecinema.base.BindingFragment
import com.sun.homecinema.base.RxViewModel

class MovieTypeFragment : BindingFragment<FragmentMovieGenreBinding>() {

    override fun getLayoutResId() = R.layout.fragment_movie_genre
    override lateinit var viewModel: RxViewModel

    override fun setupView() {
    }
}
