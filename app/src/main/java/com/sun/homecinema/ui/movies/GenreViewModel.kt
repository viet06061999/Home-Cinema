package com.sun.homecinema.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.homecinema.base.RxViewModel
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.data.repository.MovieRepository
import com.sun.homecinema.utils.GenreUtil.getIdGenre
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers


class GenreViewModel(
    private val movieRepository: MovieRepository
) : RxViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

     fun getActionMovies() {
        movieRepository.getMoviesByGenreId(getIdGenre(ACTION_MOVIE))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _movies.value = it },
                {}
            )
            .addTo(disposables)
    }

     fun getThrillerMovies() {
        movieRepository.getMoviesByGenreId(getIdGenre(THRILLER_MOVIE))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _movies.value = it },
                {}
            )
            .addTo(disposables)
    }

     fun getSciMovies() {
        movieRepository.getMoviesByGenreId(getIdGenre(SCI_MOVIE))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _movies.value = it },
                {}
            )
            .addTo(disposables)
    }

     fun getHorrorMovies() {
        movieRepository.getMoviesByGenreId(getIdGenre(HORROR_MOVIE))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _movies.value = it },
                {}
            )
            .addTo(disposables)
    }

     fun getRomanceMovies() {
        movieRepository.getMoviesByGenreId(getIdGenre(ROMANCE_MOVIE))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _movies.value = it },
                {}
            )
            .addTo(disposables)
    }

     fun getComedyMovies() {
        movieRepository.getMoviesByGenreId(getIdGenre(COMEDY_MOVIE))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _movies.value = it },
                {}
            )
            .addTo(disposables)
    }
    
    companion object{
        private const val ACTION_MOVIE = "Action"
        private const val THRILLER_MOVIE = "Thriller"
        private const val SCI_MOVIE = "Sci-Fi"
        private const val HORROR_MOVIE= "Horror"
        private const val ROMANCE_MOVIE= "Romance"
        private const val COMEDY_MOVIE= "Comedy"
    }
}
