package com.sun.homecinema.data.source.local.database.dao

import androidx.room.*
import com.sun.homecinema.data.model.Actor
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.data.model.MovieWithActors
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movie")
     fun getMovieWithActors(): Flowable<List<MovieWithActors>>

    @Query("SELECT * FROM movie WHERE movieId = :movieId")
    fun isFavorite(movieId: Int): Single<List<Movie>>

    @Delete
     fun delete(movie: Movie, actors: List<Actor>): Completable
}
