package com.sun.homecinema.ui.home

import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sun.homecinema.R
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.data.model.MovieType
import com.sun.homecinema.databinding.FragmentHomeBinding
import com.sun.homecinema.ui.adapter.MovieAdapter
import com.sun.homecinema.ui.adapter.PopularAdapter
import com.sun.homecinema.ui.adapter.UpcomingAdapter
import com.sun.mvvm.base.BindingFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BindingFragment<FragmentHomeBinding>(), View.OnClickListener {

    private val homeViewModel by sharedViewModel<HomeViewModel>()

    private val popularAdapter = PopularAdapter(::onItemClick)
    private val upcomingAdapter = UpcomingAdapter(::onItemClick)
    private val topRateAdapter = MovieAdapter(::onItemClick)

    override fun getLayoutResId() = R.layout.fragment_home

    override fun setupView() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerViewUpcoming.adapter = upcomingAdapter
        binding.recyclerViewPopular.adapter = popularAdapter
        binding.recyclerViewTopRate.adapter = topRateAdapter
        observer()
        initListener()
    }

    override fun onClick(view: View?) {
        var title = ""
        when (view?.id) {
            binding.textSeeAllPopular.id -> {
                homeViewModel.setMovies(MovieType.Popular)
                title = TITLE_POPULAR
            }
            binding.textSeeAllUpcoming.id -> {
                homeViewModel.setMovies(MovieType.Upcoming)
                title = TITLE_UPCOMING
            }
            binding.textSeeAllTopRate.id -> {
                homeViewModel.setMovies(MovieType.TopRate)
                title = TITLE_TOP_RATE
            }
        }
        val action = HomeFragmentDirections.actionHomeToList(title)
        findNavController().navigate(action)
    }

    private fun observer() = with(homeViewModel) {
        popularMovies.observe(viewLifecycleOwner, Observer(popularAdapter::submitList))
        upcomingMovies.observe(viewLifecycleOwner, Observer(upcomingAdapter::submitList))
        topRateMovies.observe(viewLifecycleOwner, Observer(topRateAdapter::submitList))
        error.observe(viewLifecycleOwner, Observer(this@HomeFragment::showToast))
    }

    private fun initListener() {

        binding.textSeeAllPopular.setOnClickListener(this)

        binding.textSeeAllTopRate.setOnClickListener(this)

        binding.textSeeAllUpcoming.setOnClickListener(this)
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
