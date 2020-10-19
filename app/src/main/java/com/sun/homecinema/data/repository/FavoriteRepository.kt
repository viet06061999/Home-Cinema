package com.sun.homecinema.data.repository

import com.sun.homecinema.data.model.MovieWithActors
import io.reactivex.Completable
import io.reactivex.Flowable

interface FavoriteRepository {

    fun getFavorites(): Flowable<List<MovieWithActors>>

    fun insertFavorite(movieWithActors: MovieWithActors): Completable

    fun deleteFavorite(movieWithActors: MovieWithActors): Completable
}
