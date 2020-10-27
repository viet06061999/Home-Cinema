package com.sun.homecinema.ui.detail

import androidx.lifecycle.*
import com.sun.homecinema.base.RxViewModel
import com.sun.homecinema.data.model.*
import com.sun.homecinema.data.repository.FavoriteRepository
import com.sun.homecinema.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

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

    private val _recommends = MutableLiveData<List<Movie>>()
    val recommends: LiveData<List<Movie>>
        get() = _recommends

    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>>
        get() = _actors

    fun loadDetail(movieId: Int) {
        getDetail(movieId)
        getActors(movieId)
        getRecommend(movieId)
        getVideo(movieId)
        checkFavorite(movieId)
    }

    private fun getDetail(movieId: Int) {
        movieRepository.getDetailMovie(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _detail.value = it },
                { error.value = it.message }
            )
            .addTo(disposables)
    }

    private fun getActors(movieId: Int) {
        movieRepository.getActors(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _actors.value = it },
                { error.value = it.message }
            )
            .addTo(disposables)
    }

    private fun checkFavorite(movieId: Int) {
        favoriteRepository.isFavorite(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _isFavorite.value = it },
                { error.value = it.message }
            )
            .addTo(disposables)
    }

    private fun getRecommend(movieId: Int) {
        movieRepository.getRecommendMovies(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _recommends.value = it },
                { error.value = it.message }
            )
            .addTo(disposables)
    }

    private fun getVideo(movieId: Int) {
        movieRepository.getVideo(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _video.value = it },
                { error.value = it.message }
            )
            .addTo(disposables)
    }

    private fun updateFavorite() {
        detail.value?.let { movie ->
            val movieWithActors = MovieWithActors(
                Movie(movie),
                actors.value ?: listOf()
            )
            if (isFavorite.value == true) {
                favoriteRepository.deleteFavorite(movieWithActors)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { _isFavorite.value = false },
                        { error.value = it.message }
                    )
                    .addTo(disposables)
            } else {
                favoriteRepository.insertFavorite(movieWithActors)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { _isFavorite.value = true },
                        { error.value = it.message }
                    )
                    .addTo(disposables)
            }
        }
    }
}
