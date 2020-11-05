package com.sun.homecinema.data.source

import com.sun.homecinema.data.model.MovieWithActors
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface FavoriteDataSource {

    interface Local {

        fun getFavorites(): Flowable<List<MovieWithActors>>

        fun insertFavorites(movieWithActors: MovieWithActors): Completable

        fun deleteFavorites(movieWithActors: MovieWithActors): Completable

        fun isFavorite(movieId: Int): Single<Boolean>
    }
}
