package com.sun.homecinema.ui.detail

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sun.homecinema.R
import com.sun.homecinema.databinding.FragmentDetailMovieBinding
import com.sun.homecinema.base.BindingFragment
import com.sun.homecinema.data.model.Actor
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.ui.adapter.ActorAdapter
import com.sun.homecinema.ui.adapter.GenreAdapter
import com.sun.homecinema.ui.adapter.RecommendAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieFragment : BindingFragment<FragmentDetailMovieBinding>() {

    override fun getLayoutResId() = R.layout.fragment_detail_movie

    override val viewModel by viewModel<DetailViewModel>()

    private val args: DetailMovieFragmentArgs by navArgs()
    private val actorAdapter = ActorAdapter(::onItemActorClick)
    private val genreAdapter = GenreAdapter()
    private val recommendAdapter = RecommendAdapter(::onItemMovieClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDetail(args.movieId)
    }

    override fun setupView() {
        binding.apply {
            lifecycleOwner = this@DetailMovieFragment
            recyclerActor.adapter = actorAdapter
            recyclerViewGenre.adapter = genreAdapter
            recyclerViewRecommend.adapter = recommendAdapter
            detailViewModel = viewModel
        }

        initListener()
    }

    private fun initListener() {
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.imageButtonPlay.setOnClickListener {
            val action = DetailMovieFragmentDirections.actioDetailToTrailer()
            findNavController().navigate(action)
        }
    }

    private fun onItemActorClick(item: Actor) {
    }

    private fun onItemMovieClick(item: Movie) {
        val action = DetailMovieFragmentDirections.actionDetailToItself(item.movieId)
        findNavController().navigate(action)
    }
}
