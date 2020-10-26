package com.sun.homecinema.ui.movies

import com.sun.homecinema.R
import com.sun.homecinema.databinding.FragmentMoviesBinding
import com.sun.homecinema.base.BindingFragment
import com.sun.homecinema.base.RxViewModel

class MoviesFragment : BindingFragment<FragmentMoviesBinding>()  {

    override fun getLayoutResId() = R.layout.fragment_movies

    override val viewModel: RxViewModel
        get() = TODO("Not yet implemented")

    override fun setupView() {
    }
}
