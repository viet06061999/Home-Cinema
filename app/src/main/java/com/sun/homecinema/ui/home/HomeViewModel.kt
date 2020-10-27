package com.sun.homecinema.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.homecinema.base.RxViewModel
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.data.model.MovieType
import com.sun.homecinema.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val movieRepository: MovieRepository
) : RxViewModel() {

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>>
        get() = _popularMovies
    private val _upcomingMovies = MutableLiveData<List<Movie>>()
    val upcomingMovies: LiveData<List<Movie>>
        get() = _upcomingMovies
    private val _topRateMovies = MutableLiveData<List<Movie>>()
    val topRateMovies: LiveData<List<Movie>>
        get() = _topRateMovies
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    init {
        getPopular()
        getUpcoming()
        getTopRate()
    }

    private fun getPopular() {
        movieRepository.getMoviesByType(MovieType.Popular)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _popularMovies.value = it },
                { error.value = it.message }
            )
            .addTo(disposables)
    }

    private fun getUpcoming() {
        movieRepository.getMoviesByType(MovieType.Upcoming)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _upcomingMovies.value = it },
                { error.value = it.message }
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

    private fun getTopRate() {
        movieRepository.getMoviesByType(MovieType.TopRate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _topRateMovies.value = it },
                { error.value = it.message }
            )
            .addTo(disposables)
    }
}
