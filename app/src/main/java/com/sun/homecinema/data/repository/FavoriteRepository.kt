package com.sun.homecinema.data.repository

import com.sun.homecinema.data.model.MovieWithActors
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface FavoriteRepository {

    fun getFavorites(): Flowable<List<MovieWithActors>>

    fun insertFavorite(movieWithActors: MovieWithActors): Completable

    fun deleteFavorite(movieWithActors: MovieWithActors): Completable

    fun isFavorite(movieId: Int): Single<Boolean>
}
