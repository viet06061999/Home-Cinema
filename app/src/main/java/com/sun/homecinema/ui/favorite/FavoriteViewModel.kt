package com.sun.homecinema.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.homecinema.base.RxViewModel
import com.sun.homecinema.data.model.Actor
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.data.model.MovieWithActors
import com.sun.homecinema.data.repository.FavoriteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class FavoriteViewModel(
    private val favoriteRepository: FavoriteRepository
) : RxViewModel() {

    private val _favorite = MutableLiveData<List<MovieWithActors>>()
    val favorite: LiveData<List<MovieWithActors>>
        get() = _favorite

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>>
        get() = _actors

    init {
        getFavorite()
    }

    private fun getFavorite() {
        favoriteRepository.getFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _favorite.value = it
                    _movies.value = it.map { favorite -> favorite.movie }
                },
                { error.value = it.message }
            )
            .addTo(disposables)
    }

    fun deleteFavorite(movieWithActors: MovieWithActors) {
        favoriteRepository.deleteFavorite(movieWithActors)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {},
                { error.value = it.message }
            )
            .addTo(disposables)
    }

    fun setActors(movieId: Int) {
        _actors.value = _favorite.value?.first {
            it.movie.movieId == movieId
        }?.actors
    }
}
