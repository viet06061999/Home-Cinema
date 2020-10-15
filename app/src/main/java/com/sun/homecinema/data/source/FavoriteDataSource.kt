package com.sun.homecinema.data.source

import com.sun.homecinema.data.model.MovieWithActors
import io.reactivex.Completable
import io.reactivex.Flowable

interface FavoriteDataSource {

    interface Local {

        fun getFavorites(): Flowable<List<MovieWithActors>>

        fun insertFavorites(movieWithActors: MovieWithActors): Completable

        fun deleteFavorites(movieWithActors: MovieWithActors): Completable
    }
}
