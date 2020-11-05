package com.sun.homecinema.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.homecinema.base.RxViewModel
import com.sun.homecinema.data.model.SearchResponse
import com.sun.homecinema.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchViewModel(
    private val movieRepository: MovieRepository
) : RxViewModel() {

    private val _search = MutableLiveData<List<SearchResponse>>()
    val search: LiveData<List<SearchResponse>>
        get() = _search

    fun search(param: String) {
        movieRepository.search(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _search.value = it },
                {}
            )
            .addTo(disposables)
    }
}
