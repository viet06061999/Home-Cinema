package com.sun.homecinema.ui.detail

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
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
import com.sun.homecinema.utils.showAndHide
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieFragment : BindingFragment<FragmentDetailMovieBinding>() {

    override fun getLayoutResId() = R.layout.fragment_detail_movie

    override val viewModel by viewModel<DetailViewModel>()
    private val args: DetailMovieFragmentArgs by navArgs()

    private val actorAdapter = ActorAdapter (::onItemActorClick)
    private val genreAdapter = GenreAdapter {}
    private val recommendAdapter =
        RecommendAdapter(::onItemMovieClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDetail(args.movieId)
    }

    override fun setupView() {
        binding.lifecycleOwner = this
        binding.recyclerActor.adapter = actorAdapter
        binding.recyclerViewGenre.adapter = genreAdapter
        binding.recyclerViewRecommend.adapter = recommendAdapter
        binding.detailViewModel = viewModel
        initListener()
    }

    private fun initListener() {
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.imageButtonFavorite.setOnClickListener {
            viewModel.updateFavorite()
        }
        binding.imageButtonPlay.setOnClickListener {
            if(viewModel.video.value != null){
                val action =
                    DetailMovieFragmentDirections.actioDetailToTrailer(viewModel.detail.value?.id ?: -1)
                findNavController().navigate(action)
            }else{
             showToast("The movie Don't have trailer")
            }
        }
    }

    private fun onItemMovieClick(item: Movie) {
        val action = DetailMovieFragmentDirections.actionDetailToItself(item.movieId)
        findNavController().navigate(action)
    }

    private fun onItemActorClick(item: Actor) {
        val action = DetailMovieFragmentDirections.actionDetailToActor(item.castId)
        findNavController().navigate(action)
    }
}
