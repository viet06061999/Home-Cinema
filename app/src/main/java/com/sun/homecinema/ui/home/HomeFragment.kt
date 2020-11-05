package com.sun.homecinema.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.sun.homecinema.R
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.data.model.MovieType
import com.sun.homecinema.databinding.FragmentHomeBinding
import com.sun.homecinema.ui.adapter.MovieAdapter
import com.sun.homecinema.ui.adapter.PopularAdapter
import com.sun.homecinema.ui.adapter.UpcomingAdapter
import com.sun.homecinema.base.BindingFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BindingFragment<FragmentHomeBinding>(), View.OnClickListener {

    override val viewModel by sharedViewModel<HomeViewModel>()

    private val popularAdapter = PopularAdapter(::onItemClick) {
        viewModel.getPopular()
    }
    private val upcomingAdapter = UpcomingAdapter(::onItemClick) {
        viewModel.getUpcoming()
    }
    private val topRateAdapter = MovieAdapter(::onItemClick) {
        viewModel.getTopRate()
    }

    override fun getLayoutResId() = R.layout.fragment_home

    override fun setupView() {
        binding.lifecycleOwner = viewLifecycleOwner
        if(viewModel.currentPageTopRate == 0){
            viewModel.getTopRate()
        }
        if(viewModel.currentPagePopular == 0){
            viewModel.getPopular()
        }
        if(viewModel.currentPageUpComming == 0){
            viewModel.getUpcoming()
        }
        binding.homeVM = viewModel
        binding.recyclerViewUpcoming.adapter = upcomingAdapter
        binding.recyclerViewPopular.adapter = popularAdapter
        binding.recyclerViewTopRate.adapter = topRateAdapter
        initListener()
    }

    override fun onClick(view: View?) {
        var title = ""
        when (view?.id) {
            binding.textSeeAllPopular.id -> {
                viewModel.setMovies(MovieType.Popular)
                title = TITLE_POPULAR
            }
            binding.textSeeAllUpcoming.id -> {
                viewModel.setMovies(MovieType.Upcoming)
                title = TITLE_UPCOMING
            }
            binding.textSeeAllTopRate.id -> {
                viewModel.setMovies(MovieType.TopRate)
                title = TITLE_TOP_RATE
            }
        }
        val action = HomeFragmentDirections.actionHomeToList(title)
        findNavController().navigate(action)
    }

    private fun initListener() {

        binding.textSeeAllPopular.setOnClickListener(this)

        binding.textSeeAllTopRate.setOnClickListener(this)

        binding.textSeeAllUpcoming.setOnClickListener(this)

        binding.imageButtonSearch.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToSearch()
            findNavController().navigate(action)
        }
    }

    private fun onItemClick(item: Movie) {
        val action = HomeFragmentDirections.actionHomeToDetail(item.movieId)
        findNavController().navigate(action)
    }

    companion object {
        private const val TITLE_POPULAR = "Popular Movies"
        private const val TITLE_UPCOMING = "Upcoming Movies"
        private const val TITLE_TOP_RATE = "Top Rate Movies"
    }
}
