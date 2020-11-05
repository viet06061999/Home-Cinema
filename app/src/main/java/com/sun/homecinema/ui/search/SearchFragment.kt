package com.sun.homecinema.ui.search

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding4.widget.queryTextChanges
import com.sun.homecinema.R
import com.sun.homecinema.base.BindingFragment
import com.sun.homecinema.data.model.SearchResponse
import com.sun.homecinema.databinding.FragmentSearchBinding
import com.sun.homecinema.ui.adapter.SearchAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BindingFragment<FragmentSearchBinding>() {

    override fun getLayoutResId() = R.layout.fragment_search

    override val viewModel by viewModel<SearchViewModel>()
    private val searchAdapter = SearchAdapter(::onItemSearchClick)

    private var disposeable: Disposable? = null

    override fun setupView() {
        binding.searchView.requestFocus()
        val imm: InputMethodManager? =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        binding.apply {
            lifecycleOwner = this@SearchFragment
            searchVM = viewModel
            recyclerViewSearch.adapter = searchAdapter
        }
        initListener()
    }

    private fun onItemSearchClick(item: SearchResponse) {
        if (item.mediaType == SearchResponse.MOVIE) {
            val action = SearchFragmentDirections.actionSearchToMovie(item.id)
            findNavController().navigate(action)
        } else {
            val action = SearchFragmentDirections.actionSearchToActor(item.id)
            findNavController().navigate(action)
        }
    }

    private fun initListener() {
        binding.imageBack.setOnClickListener {
            findNavController().popBackStack()
        }

       searchView.queryTextChanges()
            .skipInitialValue()
//            .debounce(2, TimeUnit.SECONDS)
            .map {
                println(it.toString())
                it.toString()
            }
            .switchMap { query ->
                viewModel.search(query)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    println(it.size)
                    viewModel.setValueSearch(it)
                },
                {}
            )
    }

    override fun onPause() {
        super.onPause()
        val imm: InputMethodManager? =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
}
