package com.sun.homecinema.ui.playtrailer

import com.sun.homecinema.R
import com.sun.homecinema.base.BindingFragment
import com.sun.homecinema.base.RxViewModel
import com.sun.homecinema.databinding.FragmentPlayTrailerBinding
import com.sun.homecinema.ui.detail.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class PlayTrailerFragment : BindingFragment<FragmentPlayTrailerBinding>() {

    override fun getLayoutResId() = R.layout.fragment_play_trailer

    private val detailViewModel by sharedViewModel<DetailViewModel>()
    override val viewModel: RxViewModel
        get() = TODO("Not yet implemented")

    override fun setupView() {
    }
}
