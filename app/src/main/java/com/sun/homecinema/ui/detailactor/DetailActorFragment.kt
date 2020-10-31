package com.sun.homecinema.ui.detailactor

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sun.homecinema.R
import com.sun.homecinema.base.BindingFragment
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.databinding.FragmentDetailActorBinding
import com.sun.homecinema.ui.adapter.GenreAdapter
import com.sun.homecinema.ui.adapter.RecommendAdapter
import com.sun.homecinema.ui.detail.DetailMovieFragmentDirections
import com.sun.homecinema.utils.setHeight
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActorFragment : BindingFragment<FragmentDetailActorBinding>() {

    override fun getLayoutResId() = R.layout.fragment_detail_actor

    override val viewModel by viewModel<DetailActorViewModel>()
   private val args: DetailActorFragmentArgs by navArgs()

    private val genreAdapter = GenreAdapter {}
    private val recommendAdapter =
        RecommendAdapter(::onItemMovieClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDetail(args.actorId)
    }

    override fun setupView() {
        binding.apply {
            lifecycleOwner = this@DetailActorFragment
            recyclerViewMovies.adapter = recommendAdapter
            detailActor = viewModel
            imageActor.setHeight(0.75f)
        }
        initListener()
    }

    private fun initListener() {
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onItemMovieClick(item: Movie) {
        val action = DetailActorFragmentDirections.actionActorToDetailMovie(item.movieId)
        findNavController().navigate(action)
    }
}
