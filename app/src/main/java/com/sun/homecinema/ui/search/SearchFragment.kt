package com.sun.homecinema.ui.search

import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.navigation.fragment.findNavController
import com.sun.homecinema.R
import com.sun.homecinema.base.BindingFragment
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.data.model.SearchResponse
import com.sun.homecinema.databinding.FragmentSearchBinding
import com.sun.homecinema.ui.adapter.SearchAdapter
import com.sun.homecinema.ui.adapter.WatchMoreAdapter
import com.sun.homecinema.ui.favorite.FavoriteFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BindingFragment<FragmentSearchBinding>() {

    override fun getLayoutResId() = R.layout.fragment_search

    override val viewModel by viewModel<SearchViewModel>()
    private val searchAdapter = SearchAdapter(::onItemSearchClick)

    override fun setupView() {
        initListener()
        binding.apply {
            lifecycleOwner = this@SearchFragment
            searchVM = viewModel
            recyclerViewSearch.adapter = searchAdapter
        }
    }

    private fun onItemSearchClick(item: SearchResponse) {
        if (item.mediaType == SearchResponse.MOVIE){
            val action = SearchFragmentDirections.actionSearchToMovie(item.id)
            findNavController().navigate(action)
        }else{
            val action = SearchFragmentDirections.actionSearchToActor(item.id)
            findNavController().navigate(action)
        }
    }

    private fun initListener() {
        binding.imageBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.search(it) }
                return true
            }
        })
    }
}
