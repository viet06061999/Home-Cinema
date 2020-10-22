package com.sun.homecinema.ui.detail

import androidx.navigation.fragment.navArgs
import com.sun.homecinema.R
import com.sun.homecinema.databinding.FragmentDetailMovieBinding
import com.sun.mvvm.base.BindingFragment

class DetailMovieFragment : BindingFragment<FragmentDetailMovieBinding>() {

    override fun getLayoutResId() = R.layout.fragment_detail_movie

    val args: DetailMovieFragmentArgs by navArgs()

    override fun setupView() {
    }
}
