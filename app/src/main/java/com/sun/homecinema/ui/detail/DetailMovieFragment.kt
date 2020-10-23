package com.sun.homecinema.ui.detail

import androidx.navigation.fragment.navArgs
import com.sun.homecinema.R
import com.sun.homecinema.databinding.FragmentDetailMovieBinding
import com.sun.homecinema.base.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieFragment : BindingFragment<FragmentDetailMovieBinding>() {

    override fun getLayoutResId() = R.layout.fragment_detail_movie
    override val viewModel by viewModel<DetailViewModel>()

    val args: DetailMovieFragmentArgs by navArgs()

    override fun setupView() {
    }
}
