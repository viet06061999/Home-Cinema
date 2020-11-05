package com.sun.homecinema.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.homecinema.base.RxViewModel
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.data.model.MovieType
import com.sun.homecinema.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(
    private val movieRepository: MovieRepository
) : RxViewModel() {
    var currentPagePopular = 0
    var currentPageUpComming = 0
    var currentPageTopRate = 0

    private val _popularMovies = MutableLiveData<MutableList<Movie>>()
    val popularMovies: LiveData<MutableList<Movie>>
        get() = _popularMovies
    private val _upcomingMovies = MutableLiveData<MutableList<Movie>>()
    val upcomingMovies: LiveData<MutableList<Movie>>
        get() = _upcomingMovies
    private val _topRateMovies = MutableLiveData<MutableList<Movie>>()
    val topRateMovies: LiveData<MutableList<Movie>>
        get() = _topRateMovies
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    init {
        getPopular()
        getUpcoming()
        getTopRate()
    }

    fun getPopular() {
        movieRepository.getMoviesByType(MovieType.Popular, ++currentPagePopular)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (currentPagePopular == 1) _popularMovies.value = it as MutableList<Movie>?
                    else _popularMovies.value?.addAll(it)
                },
                {
                    error.value = it.message
                    currentPagePopular--
                }
            )
            .addTo(disposables)
    }

    fun getUpcoming() {
        movieRepository.getMoviesByType(MovieType.Upcoming, ++currentPageUpComming)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (currentPageUpComming == 1) _upcomingMovies.value = it as MutableList<Movie>?
                    else _upcomingMovies.value?.addAll(it)
                },
                {
                    error.value = it.message
                    currentPageUpComming--
                }
            )
            .addTo(disposables)
    }

    fun setMovies(type: MovieType) {
        when (type) {
            MovieType.Popular -> _movies.value = _popularMovies.value
            MovieType.TopRate -> _movies.value = _topRateMovies.value
            MovieType.Upcoming -> _movies.value = _upcomingMovies.value
        }
    }

    fun getTopRate() {
        movieRepository.getMoviesByType(MovieType.TopRate, ++currentPageTopRate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (currentPageTopRate == 1) _topRateMovies.value = it as MutableList<Movie>?
                    else _topRateMovies.value?.addAll(it)
                },
                {
                    error.value = it.message
                    currentPageTopRate--
                }
            )
            .addTo(disposables)
    }
}
