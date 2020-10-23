package com.sun.homecinema.ui.detail

import androidx.lifecycle.*
import com.sun.homecinema.base.RxViewModel
import com.sun.homecinema.data.model.*
import com.sun.homecinema.data.repository.FavoriteRepository
import com.sun.homecinema.data.repository.MovieRepository

class DetailViewModel(
    private val movieRepository: MovieRepository,
    private val favoriteRepository: FavoriteRepository
) : RxViewModel() {

    private val _detail = MutableLiveData<MovieResponse>()
    val detail: LiveData<MovieResponse>
        get() = _detail

    private val _video = MutableLiveData<Video>()
    val video: LiveData<Video>
        get() = _video

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    private val _recommends = mutableListOf<Movie>()
    val recommends: List<Movie>
        get() = _recommends

    private val _actors = mutableListOf<Actor>()
    val actors: List<Actor>
        get() = _actors
}
