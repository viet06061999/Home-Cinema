package com.sun.homecinema.data.repository

import com.sun.homecinema.data.model.MovieWithActors
import com.sun.homecinema.data.source.FavoriteDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class FavoriteRepositoryImpl(private val local: FavoriteDataSource.Local) :
    FavoriteRepository {

    override fun getFavorites(): Flowable<List<MovieWithActors>> =
        local.getFavorites()

    override fun insertFavorite(movieWithActors: MovieWithActors): Completable =
        local.insertFavorites(movieWithActors)

    override fun deleteFavorite(movieWithActors: MovieWithActors): Completable =
        local.deleteFavorites(movieWithActors)

    override fun isFavorite(movieId: Int): Single<Boolean> =
        local.isFavorite(movieId)
}
