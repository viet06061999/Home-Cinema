package com.sun.homecinema.ui.detailactor

import androidx.lifecycle.*
import com.sun.homecinema.base.RxViewModel
import com.sun.homecinema.data.model.*
import com.sun.homecinema.data.repository.ActorRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class DetailActorViewModel(private val actorRepository: ActorRepository) : RxViewModel() {

    private val _detail = MutableLiveData<Actor>()
    val detail: LiveData<Actor>
        get() = _detail

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    fun loadDetail(movieId: Int) {
        getDetail(movieId)
        getMovies(movieId)
    }

    private fun getDetail(actorId: Int) {
        actorRepository.getDetailActor(actorId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _detail.value = it },
                {
                    error.value = it.message
                    it.printStackTrace()
                }
            )
            .addTo(disposables)
    }

    private fun getMovies(actorId: Int) {
        println(actorId)
        actorRepository.getMoviesOfActor(actorId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _movies.value = it
                    println(it)
                },
                {
                    error.value = it.message
                    it.printStackTrace()
                }
            )
            .addTo(disposables)
    }
}
