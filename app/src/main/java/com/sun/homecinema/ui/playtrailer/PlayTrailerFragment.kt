package com.sun.homecinema.ui.playtrailer

import com.sun.homecinema.R
import com.sun.homecinema.base.BindingFragment
import com.sun.homecinema.databinding.FragmentPlayTrailerBinding
import com.sun.homecinema.ui.detail.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayTrailerFragment : BindingFragment<FragmentPlayTrailerBinding>() {

    override fun getLayoutResId() = R.layout.fragment_play_trailer

    override val viewModel by viewModel<DetailViewModel>()

    override fun setupView() {
    }
}
