package com.sun.homecinema.ui.favorite

import com.sun.homecinema.R
import com.sun.homecinema.databinding.FragmentFavoriteBinding
import com.sun.homecinema.base.BindingFragment
import com.sun.homecinema.base.RxViewModel

class FavoriteFragment : BindingFragment<FragmentFavoriteBinding>() {

    override fun getLayoutResId() = R.layout.fragment_favorite

    override val viewModel: RxViewModel
        get() = TODO("Not yet implemented")

    override fun setupView() {
    }
}
