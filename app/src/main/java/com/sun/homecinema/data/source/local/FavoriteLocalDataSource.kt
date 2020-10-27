package com.sun.homecinema.data.source.local

import com.sun.homecinema.data.model.MovieWithActors
import com.sun.homecinema.data.source.FavoriteDataSource
import com.sun.homecinema.data.source.local.database.dao.ActorDao
import com.sun.homecinema.data.source.local.database.dao.MovieDao
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import java.lang.Exception

class FavoriteLocalDataSource(
    private val movieDao: MovieDao,
    private val actorDao: ActorDao
) : FavoriteDataSource.Local {

    override fun getFavorites(): Flowable<List<MovieWithActors>> =
        movieDao.getMovieWithActors()

    override fun insertFavorites(movieWithActors: MovieWithActors) =
        Completable.create { emitter ->
            movieWithActors.actors.forEach {
                it.castMovieId = movieWithActors.movie.movieId
            }
            try {
                movieDao.insertMovie(movieWithActors.movie)
                actorDao.insertActors(movieWithActors.actors)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

    override fun deleteFavorites(movieWithActors: MovieWithActors) =
        movieDao.delete(movieWithActors.movie, movieWithActors.actors)

    override fun isFavorite(movieId: Int): Single<Boolean> =
        movieDao.isFavorite(movieId)
}
