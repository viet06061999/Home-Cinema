package com.sun.homecinema.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.homecinema.base.RxViewModel
import com.sun.homecinema.data.model.SearchResponse
import com.sun.homecinema.data.repository.MovieRepository
import io.reactivex.rxjava3.core.Observable

class SearchViewModel(
    private val movieRepository: MovieRepository
) : RxViewModel() {

    private val _search = MutableLiveData<List<SearchResponse>>()
    val search: LiveData<List<SearchResponse>>
        get() = _search

    fun search(param: String): Observable<List<SearchResponse>> = movieRepository.search(param)

    fun setValueSearch(listSearch: List<SearchResponse>) {
        _search.value = listSearch
    }

    companion object {
        private const val ACTION_MOVIE = "Action"
        private const val THRILLER_MOVIE = "Thriller"
        private const val SCI_MOVIE = "Sci-Fi"
        private const val HORROR_MOVIE = "Horror"
        private const val ROMANCE_MOVIE = "Romance"
        private const val COMEDY_MOVIE = "Comedy"
    }
}
